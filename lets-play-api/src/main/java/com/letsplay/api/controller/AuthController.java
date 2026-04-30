package com.letsplay.api.controller;

import com.letsplay.api.dto.LoginRequestDto;
import com.letsplay.api.dto.LoginResponseDto;
import com.letsplay.api.dto.UserRegisterDto;
import com.letsplay.api.dto.UserResponseDto;
import com.letsplay.api.entity.User;
import com.letsplay.api.service.JwtService;
import com.letsplay.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterDto dto) {

        if (userService.emailExists(dto.getEmail())) {
            return ResponseEntity.status(409)
                    .body("Un utilisateur avec cet email existe déjà");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        User saved = userService.registerUser(user);

        UserResponseDto response = new UserResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole()
        );

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) {

        Optional<User> optionalUser = userService.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }

        User user = optionalUser.get();

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!matches) {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }

        String token = jwtService.generateToken(user);

        LoginResponseDto response = new LoginResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                token
        );

        return ResponseEntity.ok(response);
    }
}
