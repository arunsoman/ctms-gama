package com.gama.whiv.notification;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationEscalationController {

    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody SendNotificationRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/escalate")
    public String escalateCase(@RequestBody EscalateCaseRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/notifications")
    public String getNotifications(@RequestParam(required = false) String status) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record SendNotificationRequest(
            String recipientId,
            String message,
            String priority
    ) {}

    public record EscalateCaseRequest(
            String caseId,
            String escalatedTo,
            String reason
    ) {}
}
