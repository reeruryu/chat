package org.example.chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.chat.controller.dto.UserDto;
import org.example.chat.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserDto.SignUpRequest request) {
        userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody UserDto.LoginRequest request) {
        String token = userService.login(request);
        return ResponseEntity.ok(token);
    }

}
