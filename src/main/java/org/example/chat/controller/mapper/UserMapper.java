package org.example.chat.controller.mapper;

import org.example.chat.controller.dto.UserDto;
import org.example.chat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto.SignUpRequest entityToDto(User user);
    User dtoToEntity(UserDto.SignUpRequest signUpRequest);
}
