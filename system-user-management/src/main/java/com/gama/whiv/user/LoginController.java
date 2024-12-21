package com.gama.whiv.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gama.whiv.user.entity.User;
import com.gama.whiv.user.jwt.JwtService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    record LoginRequest(String username, String password) {}

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.validateUser(loginRequest.username(), loginRequest.password());
        if (user != null) {
            String token = jwtService.createToken(user.getUsername());
            return ResponseEntity.ok().body("Login successful, token: " + token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
