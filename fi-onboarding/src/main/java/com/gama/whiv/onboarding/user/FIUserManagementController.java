package com.gama.whiv.onboarding.user;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created the FIUserManagementController class to manage user-related operations for Financial Institutions.
 */
@RestController
@RequestMapping("/api/user-management")
public class FIUserManagementController {

    @Autowired
    private FIUserService fiUserService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest request) {
        if (request.userName() == null || request.contactEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input data");
        }
        FIUser user = new FIUser();
        //TODO
        fiUserService.saveUser(user);
        return ResponseEntity.ok("User added successfully!");
    }

    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest request) {
        FIUser user = fiUserService.getUserById(Long.valueOf(request.userId()));
        if (user != null) {
            //TODO
            fiUserService.saveUser(user);
            return ResponseEntity.ok("User updated successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
    }

    @DeleteMapping("/remove-user/{userId}")
    public String removeUser(@PathVariable String userId) {
        fiUserService.deleteUser(Long.valueOf(userId));
        return "User removed successfully!";
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<FIUser> getUserById(@PathVariable String userId) {
        FIUser user = fiUserService.getUserById(Long.valueOf(userId));
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/list-users")
    public ResponseEntity<List<FIUser>> listUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<FIUser> usersPage = (Page<FIUser>) fiUserService.getAllUsers(PageRequest.of(page, size));
        return ResponseEntity.ok(usersPage.getContent());
    }

    public record AddUserRequest(
            String userName,
            String userRole,
            String contactEmail
    ) {}

    public record UpdateUserRequest(
            String userId,
            String newRole,
            String updatedBy
    ) {}
}
