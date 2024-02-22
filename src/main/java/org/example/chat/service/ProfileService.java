package org.example.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.chat.controller.dto.ProfileDto;
import org.example.chat.entity.Profile;
import org.example.chat.entity.User;
import org.example.chat.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    @Transactional
    public Profile createProfile(User user, ProfileDto.CreateRequest request) {
        return profileRepository.save(Profile.builder()
                .nickname(request.getNickName())
                .user(user)
                .build());
    }
}
