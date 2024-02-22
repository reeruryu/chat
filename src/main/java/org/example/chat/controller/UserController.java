package org.example.chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.chat.controller.dto.ProfileDto;
import org.example.chat.controller.dto.UserDto;
import org.example.chat.entity.User;
import org.example.chat.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    /* // test api
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestParam("name") String userName) {
        User user = userRepository.save(User.builder().userName(userName).build());
        return ResponseEntity.ok(user.getId());
    }*/

    @PostMapping("signup")
    public ResponseEntity<Long> signUp(@Valid @RequestBody UserDto.SignUpRequest request) {
        Long userId = userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody UserDto.LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/{userId}/profile")
    public ResponseEntity<Void> createProfile(
            @PathVariable Long userId, @Valid @RequestBody ProfileDto.CreateRequest request
    ) {
        userService.createProfile(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /*@GetMapping
    public ResponseEntity<String> getUser(@AuthenticationPrincipal Long userId) {
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user.getUserName());
    }*/

}
