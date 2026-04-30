package com.letsplay.api.controller;

import com.letsplay.api.dto.UserAdminDto;
import com.letsplay.api.entity.User;
import com.letsplay.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserAdminDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserAdminDto> dtos = users.stream()
                .map(u -> new UserAdminDto(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getRole()
                ))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAdminDto> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(u -> new UserAdminDto(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getRole()
                ))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
