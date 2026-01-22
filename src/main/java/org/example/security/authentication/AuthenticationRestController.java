package org.example.security.authentication;

import org.example.security.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@RequestBody LoginRequest request) {

        // ⚠️ Hardcodeado por ahora (luego DB)
        if (!request.username().equals("admin") ||
                !request.password().equals("admin123")) {

            return Mono.error(new RuntimeException("Invalid credentials"));
        }

        String token = JwtUtil.generateToken(request.username());

        return Mono.just(Map.of("token", token));
    }
}
