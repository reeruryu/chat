package org.example.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.chat.common.exception.BusinessExceptionHandler;
import org.example.chat.common.exception.ErrorCode;
import org.example.chat.entity.User;
import org.example.chat.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.ERROR_001));
        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
