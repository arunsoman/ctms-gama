package com.gama.whiv.user.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Autowired
    private JwtUtil jwtUtil;

    public String createToken(String username) {
        return jwtUtil.generateToken(username);
    }

    public Boolean validateToken(String token, String username) {
        return jwtUtil.validateToken(token, username);
    }
}
