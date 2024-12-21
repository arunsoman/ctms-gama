package com.gama.whiv.security;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/security")
public class SecurityAccessController {

    @PostMapping("/add-user")
    public String addUser(@RequestBody AddUserRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @PutMapping("/update-access")
    public String updateUserAccess(@RequestBody UpdateAccessRequest request) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @DeleteMapping("/remove-user/{userId}")
    public String removeUser(@PathVariable String userId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/permissions/{userId}")
    public String getUserPermissions(@PathVariable String userId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    @GetMapping("/activity-logs")
    public String getActivityLogs(@RequestParam(required = false) String userId) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }

    public record AddUserRequest(
            String username,
            String role,
            String description
    ) {}

    public record UpdateAccessRequest(
            String userId,
            String newRole,
            String updatedBy
    ) {}
}
