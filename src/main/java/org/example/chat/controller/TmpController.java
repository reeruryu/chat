package org.example.chat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.chat.controller.dto.UserDto;
import org.example.chat.entity.User;
import org.example.chat.repository.UserRepository;
import org.example.chat.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatCodePointException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tmp")
public class TmpController {

    private final UserRepository userRepository;
    @GetMapping
    public ResponseEntity<String> tmp(@AuthenticationPrincipal Long id) {
        if (id == null) return ResponseEntity.ok("null");
        return ResponseEntity.ok(userRepository.findById(id).get().getUserName());
    }
}
