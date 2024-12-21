import { Injectable, OnDestroy } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResizeService implements OnDestroy {
  private resizeObservers = new Map<Element, {
    observer: ResizeObserver,
    subject: Subject<ResizeObserverEntry[]>
  }>();

  observeResize(element: Element): Observable<ResizeObserverEntry[]> {
    // Return existing observer if one exists
    const existing = this.resizeObservers.get(element);
    if (existing) {
      return existing.subject.asObservable();
    }

    // Create new observer
    const subject = new Subject<ResizeObserverEntry[]>();
    const observer = new ResizeObserver(entries => {
      subject.next(entries);
    });

    this.resizeObservers.set(element, { observer, subject });
    observer.observe(element);

    return subject.asObservable();
  }

  stopObserving(element: Element) {
    const observer = this.resizeObservers.get(element);
    if (observer) {
      observer.observer.disconnect();
      observer.subject.complete();
      this.resizeObservers.delete(element);
    }
  }

  ngOnDestroy() {
    this.resizeObservers.forEach(({ observer, subject }) => {
      observer.disconnect();
      subject.complete();
    });
    this.resizeObservers.clear();
  }
}