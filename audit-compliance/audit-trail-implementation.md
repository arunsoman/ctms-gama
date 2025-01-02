**complete design** for creating an Angular-based audit-trail module with a **UI callback** mechanism for reason prompting, **old/new value storage**, and the **requirement to cancel the request** if no reason is provided.

---

# Angular Audit-Trail Module: Design Document

## 1. Overview

This design outlines an **Angular Audit-Trail Module** that intercepts user-triggered HTTP requests, **requires a reason** for certain actions, logs **old and new values**, and **cancels** the request if the user does not provide a reason.

### Key Goals

1. **Global Interceptor**  
   - Automatically handle logging without needing explicit “logEvent” calls in each module.  
2. **UI Callback for Reason**  
   - Provide a mechanism where the interceptor emits an event.  
   - A hosting component (or global UI) prompts the user for a reason.  
   - If the user cancels or provides no reason, **the request is canceled**.  
3. **Old/New Value Logging**  
   - Store “old value” in local/session storage right before making the request.  
   - Extract the “new value” from the response body.  
   - Include both in the final audit record.  
4. **Real-Time Logging**  
   - Immediately post logs to the audit backend (no batch insertion).  
   - Once logs are successfully sent, remove any stored old-value data.  
5. **Retention & Archiving**  
   - Handled on the backend (e.g., remove or archive records older than 6 months).

---

## 2. Use Cases

1. **Deactivating a User**  
   - Must require a reason (“User requested closure,” “Inactivity,” etc.).  
   - If the user doesn’t provide a reason, the request is aborted.  
   - The old user data (pre-deactivation status) is stored as “oldValue.”  
   - The response body with updated user data is the “newValue.”  

2. **Simple CRUD Changes**  
   - For create or update actions that need reasons (e.g., “Update user’s role”), the same flow applies: prompt reason, store oldValue, attach reason in request, fetch newValue from the response, log.  

3. **Actions That Don’t Require a Reason**  
   - The interceptor recognizes certain actions that do not require a reason. Those requests pass through normally but still log user ID, timestamp, etc.

---

## 3. Architecture

### 3.1 High-Level Components

1. **AuditInterceptor (Global HTTP Interceptor)**  
   - Captures all outbound requests.  
   - Checks if a request is “auditable” based on headers/decorators.  
   - If the action requires a reason, it **pauses** the request by requesting a reason from `AuditReasonService`.  
   - Cancels the request if the user provides no reason.  
   - Otherwise, proceeds and logs the event afterward.  

2. **AuditReasonService (UI Callback Mediator)**  
   - Maintains a Subject/Observable pattern.  
   - When the interceptor needs a reason, it calls `requestReason(actionName)`.  
   - Emits a “reason request” event so that a **hosting component** can open a modal/prompt.  
   - Once the user enters a reason, the component passes it back via a Subject to the interceptor.  

3. **Hosting Component**  
   - Subscribes to the `AuditReasonService` for reason requests.  
   - Presents a modal or prompt to gather the user’s reason.  
   - If the user cancels or leaves it blank, returns `null`; if provided, returns the string reason.  

4. **AuditTrailService**  
   - Called by the interceptor after the request completes.  
   - Sends the final audit record (user ID, timestamp, action, reason, oldValue, newValue, etc.) to the audit backend.  

### 3.2 Old/New Value Storage

- **Before the Request**:  
  - The component or service about to make a change stores the “old value” in local or session storage, keyed by the action or entity ID (e.g., `audit-oldValue-DeactivateUser`).  

- **During Interception**:  
  - On success, the interceptor looks up the “oldValue” in local storage and extracts the “newValue” from the response body.  
  - Sends them to `AuditTrailService`.  
  - Then **removes** the stored oldValue from local storage.

### 3.3 Canceling the Request If No Reason

- If the user cancels or provides an empty reason, the interceptor will **throw** an error (e.g., `throwError(() => new Error('Audit reason not provided. Request canceled.'))`).  
- This aborts the HTTP request and notifies the calling code that the request was canceled.

---

## 4. Implementation Details

### 4.1 Interceptor

**Key Duties**:
1. Detect whether the outgoing request requires auditing (e.g., a custom header: `X-Audit-Action`).  
2. If reason required, request reason from `AuditReasonService`.  
3. If reason is `null`, cancel. Otherwise, attach the reason to the request.  
4. On successful response, retrieve old/new values, log them, and clean up.

<details>
<summary>AuditInterceptor (TypeScript Example)</summary>

```ts
import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { switchMap, tap } from 'rxjs/operators';
import { AuditReasonService } from './audit-reason.service';
import { AuditTrailService } from './audit-trail.service';

@Injectable()
export class AuditInterceptor implements HttpInterceptor {
  constructor(
    private auditReasonService: AuditReasonService,
    private auditTrailService: AuditTrailService
  ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Check for audit action
    const auditAction = req.headers.get('X-Audit-Action');
    if (!auditAction) {
      // No auditing needed
      return next.handle(req);
    }

    // Determine if reason is required (based on some logic)
    const requiresReason = this.requiresReason(auditAction);
    if (!requiresReason) {
      // Just log afterwards, no reason needed
      return next.handle(req).pipe(
        tap(event => {
          if (event instanceof HttpResponse) {
            this.logAudit(auditAction, null, req, event);
          }
        })
      );
    }

    // If a reason is required, request it from the user
    return this.auditReasonService.requestReason(auditAction).pipe(
      switchMap(reason => {
        if (!reason) {
          // User canceled or didn't provide a reason => abort request
          return throwError(() => new Error('Audit reason not provided. Request canceled.'));
        }

        // Attach reason to headers
        const modifiedReq = req.clone({
          headers: req.headers.set('X-Audit-Reason', reason)
        });

        // Proceed with the request
        return next.handle(modifiedReq).pipe(
          tap(event => {
            if (event instanceof HttpResponse) {
              // After success, log with reason
              this.logAudit(auditAction, reason, modifiedReq, event);
            }
          })
        );
      })
    );
  }

  private requiresReason(auditAction: string): boolean {
    // Example logic: certain actions need reasons
    return auditAction === 'DeactivateUser' || auditAction === 'UpdateUserRole';
  }

  private logAudit(
    auditAction: string,
    reason: string | null,
    req: HttpRequest<any>,
    event: HttpEvent<any>
  ) {
    // Retrieve old value from storage
    const oldValue = this.getOldValue(auditAction);

    // Extract new value if available (assuming the response body is the updated entity)
    let newValue: any = null;
    if (event instanceof HttpResponse) {
      newValue = event.body;
    }

    // Log via the service
    this.auditTrailService.logAction({
      userId: this.getCurrentUserId(),
      timestamp: new Date(),
      action: auditAction,
      reason: reason,
      oldValue: oldValue,
      newValue: newValue
    });

    // Clean up stored old value
    this.clearOldValue(auditAction);
  }

  private getOldValue(action: string): any {
    const key = `audit-oldValue-${action}`;
    const json = localStorage.getItem(key);
    if (json) {
      return JSON.parse(json);
    }
    return null;
  }

  private clearOldValue(action: string): void {
    localStorage.removeItem(`audit-oldValue-${action}`);
  }

  private getCurrentUserId(): string {
    // Example: fetch from auth service or decode JWT
    return 'user-1234';
  }
}
```
</details>

---

### 4.2 AuditReasonService

**Key Duties**:
- Provide a method `requestReason(auditAction: string)` that returns an Observable.  
- Emit an event so a listener can prompt the user.  
- Once the user enters a reason (or cancels), emit the result back to the interceptor.

<details>
<summary>AuditReasonService (TypeScript Example)</summary>

```ts
import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';

@Injectable()
export class AuditReasonService {
  private reasonRequestSubject = new Subject<ReasonRequest>();

  /**
   * Called by the interceptor to ask for a reason.
   */
  requestReason(auditAction: string): Observable<string | null> {
    const reasonProvided$ = new Subject<string | null>();

    // Emit a request event for the UI to pick up
    this.reasonRequestSubject.next({
      action: auditAction,
      reasonSubject: reasonProvided$
    });

    // Return the Subject as an observable to the interceptor.
    return reasonProvided$.asObservable();
  }

  /**
   * Components can subscribe to this to handle reason requests.
   */
  onReasonRequest(): Observable<ReasonRequest> {
    return this.reasonRequestSubject.asObservable();
  }
}

/**
 * Describes a reason request:
 * - action: the audit action name (e.g., 'DeactivateUser')
 * - reasonSubject: a Subject that the UI must fulfill or complete with null
 */
export interface ReasonRequest {
  action: string;
  reasonSubject: Subject<string | null>;
}
```
</details>

---

### 4.3 Hosting Component

**Key Duties**:
- Subscribe to `onReasonRequest()`.  
- When a request arrives, **prompt the user** (e.g., via a modal).  
- If user provides a reason, push that reason into `reasonSubject`. If user cancels, push `null`.

<details>
<summary>AuditReasonListenerComponent (TypeScript Example)</summary>

```ts
import { Component, OnInit } from '@angular/core';
import { AuditReasonService, ReasonRequest } from './audit-reason.service';

@Component({
  selector: 'app-audit-reason-listener',
  template: ''
})
export class AuditReasonListenerComponent implements OnInit {
  constructor(private auditReasonService: AuditReasonService) {}

  ngOnInit(): void {
    this.auditReasonService.onReasonRequest().subscribe((req: ReasonRequest) => {
      // Open a modal or show a prompt for the user
      const userReason = prompt(`Enter reason for ${req.action}:`);

      // If user pressed "Cancel" or left it blank => treat as null => request canceled
      if (!userReason) {
        req.reasonSubject.next(null);
      } else {
        req.reasonSubject.next(userReason);
      }
      req.reasonSubject.complete();
    });
  }
}
```
</details>

> **Note**: In a real UI, you would likely have a more sophisticated modal/dialog flow rather than using `prompt()`. This example simply demonstrates the concept.

---

### 4.4 AuditTrailService

**Key Duties**:
- **Receive** the final audit record from the interceptor.  
- **Send** it to the backend (e.g., `POST /api/audit`).  
- Optionally handle success/failure logging or retries.

<details>
<summary>AuditTrailService (TypeScript Example)</summary>

```ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface AuditRecord {
  userId: string;
  timestamp: Date;
  action: string;
  reason?: string | null;
  oldValue?: any;
  newValue?: any;
}

@Injectable()
export class AuditTrailService {
  private readonly auditEndpoint = '/api/audit';

  constructor(private http: HttpClient) {}

  logAction(auditRecord: AuditRecord): void {
    this.http.post(this.auditEndpoint, auditRecord).subscribe({
      next: () => console.log('Audit log posted successfully.'),
      error: err => console.error('Audit log posting failed:', err)
    });
  }
}
```
</details>

---

### 4.5 Storing Old Value

**Key Pattern**:
- **Component or Service** that’s about to perform the action sets the old value in local or session storage **before** the HTTP call.  
- The **action name** or entity ID can be used to create a unique storage key.  
- The interceptor retrieves this data to include in the final audit log.

<details>
<summary>Example in a User Management Component</summary>

```ts
onDeactivateUser(user: any) {
  // Store the old user object
  localStorage.setItem(
    'audit-oldValue-DeactivateUser',
    JSON.stringify(user)
  );

  // Fire the HTTP request with our audit action
  this.http.patch('/api/users/deactivate', { userId: user.id }, {
    headers: { 'X-Audit-Action': 'DeactivateUser' }
  }).subscribe({
    next: () => console.log('User deactivated'),
    error: err => console.error('Deactivate user failed:', err)
  });
}
```
</details>

---

## 5. Example Flow

1. **User** clicks “Deactivate User” in a UI form.  
2. The **Component** stores the old user data in `localStorage` under key `audit-oldValue-DeactivateUser`.  
3. The **Component** calls `this.http.patch(...)` with header `X-Audit-Action: 'DeactivateUser'`.  
4. The **AuditInterceptor** sees `DeactivateUser` and checks it **requires** a reason.  
5. It calls `auditReasonService.requestReason('DeactivateUser')`.  
6. The **AuditReasonService** emits a `ReasonRequest` event.  
7. The **Hosting Component** shows a prompt or modal for the reason.  
   - If the user clicks “Cancel” or provides nothing, it returns `null`. The interceptor **throws an error** → **request canceled**.  
   - If the user provides a reason, e.g., “User requested closure,” it returns that string.  
8. The **Interceptor** attaches this reason in the request headers and allows the HTTP request to proceed.  
9. The **Backend** processes the request, returning updated user data.  
10. The **Interceptor** receives the `HttpResponse`, calls `logAudit()`:  
    - Retrieves old user data from localStorage.  
    - Extracts new user data from `response.body`.  
    - Invokes `auditTrailService.logAction(...)` to store the record in the audit DB.  
    - Clears the old-value from localStorage.  

---

## 6. Security & Additional Considerations

1. **Local/Session Storage Risks**  
   - Storing sensitive data in local/session storage requires caution. If old values contain PII, you might need encryption or ephemeral in-memory storage.  
2. **Permissions**  
   - Implement a mechanism to ensure only authorized roles (e.g., admin) can view the audit logs.  
3. **Archiving**  
   - The backend should handle archiving or purging logs older than 6 months.  
4. **Error Handling**  
   - When the request is canceled, the calling component can show a “Request canceled” message. Consider global error handlers.  
5. **Performance**  
   - Each audited request triggers an immediate `POST` to the audit backend. If your system experiences heavy load, you might consider queueing or batching **on the server** while still ensuring minimal data loss.  
6. **Customizing Old/New Value Logic**  
   - If your backend can determine old vs. new values automatically (e.g., via an ORM), you might do less on the front end.  
7. **Future Enhancements**  
   - Add a global config for which actions require reasons, how to handle reason prompts (modal vs. inline UI), etc.  
   - Use a dedicated Angular library with a `forRoot` method for easy configuration.

---

## 7. Conclusion

With the above **interceptor-based** and **UI-callback** approach, we achieve:

- **Seamless Integration**: Minimal code changes in individual modules—just store the old value before a request and add a header for the action name.  
- **Mandatory Reason**: If a reason is required and not provided, the request is canceled, ensuring the audit log is always complete.  
- **Old/New Value Storage**: The old value is cached locally; the new value is derived from the response. Both are passed to the audit backend in real-time.  
- **Flexibility**: A hosting component controls how the user is prompted for a reason (modal, custom form, etc.).  
- **Scalability & Clarity**: Clear separation of concerns (Interceptor for capture, ReasonService for UI callbacks, AuditTrailService for logging, etc.).

