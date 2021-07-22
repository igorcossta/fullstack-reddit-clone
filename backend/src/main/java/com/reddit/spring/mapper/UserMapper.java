package com.reddit.spring.mapper;

import com.reddit.spring.dto.LoginResponse;
import com.reddit.spring.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "token", source = "token")
    LoginResponse mapUserToDto(User user, String token);
}
