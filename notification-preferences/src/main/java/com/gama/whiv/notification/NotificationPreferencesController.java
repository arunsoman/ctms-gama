package com.gama.whiv.notification;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification-preferences")
public class NotificationPreferencesController {

    @PostMapping("/set-preference")
    public String setNotificationPreference(@RequestBody SetNotificationPreferenceRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/get-preference/{userId}")
    public String getNotificationPreference(@PathVariable String userId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PostMapping("/remove-preference/{userId}")
    public String removeNotificationPreference(@PathVariable String userId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record SetNotificationPreferenceRequest(
            String userId,
            List<String> channels, // e.g., "Email", "SMS"
            String frequency // e.g., "Immediate", "Daily"
    ) {}
}
