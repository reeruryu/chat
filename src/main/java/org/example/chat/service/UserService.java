package org.example.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.chat.common.exception.BusinessExceptionHandler;
import org.example.chat.common.exception.ErrorCode;
import org.example.chat.config.TokenProvider;
import org.example.chat.controller.dto.ProfileDto;
import org.example.chat.controller.dto.UserDto;
import org.example.chat.controller.mapper.UserMapper;
import org.example.chat.entity.Profile;
import org.example.chat.entity.User;
import org.example.chat.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    private final ProfileService profileService;

    @Transactional
    public Long signUp(UserDto.SignUpRequest request) {
        checkEmailExist(request.getEmail());
        request.setPassword(encode(request.getPassword()));

        User user = userRepository.save(UserMapper.INSTANCE.dtoToEntity(request));
        return user.getId();
    }

    @Transactional(readOnly = true)
    public String login(UserDto.LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).
                orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.ERROR_001));
        if (!matches(request.getPassword(), user.getPassword())) {
            throw new BusinessExceptionHandler(ErrorCode.ERROR_001);
        }
        return tokenProvider.createToken(user.getId().toString(), List.of("USER"));
    }

    @Transactional
    public void createProfile(Long userId, ProfileDto.CreateRequest request) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new BusinessExceptionHandler(ErrorCode.ERROR_001));

        Profile profile = profileService.createProfile(user, request);
        user.updateProfile(profile);
    }

    private String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    private boolean matches(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

    private void checkEmailExist(String email) {
        if (userRepository.existsByEmail(email))
            throw new BusinessExceptionHandler(ErrorCode.ERROR_001);
    }

}
