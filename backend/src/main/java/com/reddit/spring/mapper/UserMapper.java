package com.reddit.spring.mapper;

import com.reddit.spring.dto.LoginResponse;
import com.reddit.spring.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    LoginResponse mapUserToDto(User user, String token);
}
