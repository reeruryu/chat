package org.example.chat.controller;

import lombok.RequiredArgsConstructor;
import org.example.chat.entity.User;
import org.example.chat.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    /* test api */
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestParam("name") String userName) {
        User user = userRepository.save(User.builder().userName(userName).build());
        return ResponseEntity.ok(user.getId());
    }
}
