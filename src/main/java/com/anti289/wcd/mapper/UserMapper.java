package com.anti289.wcd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.anti289.wcd.repository.entity.UserEntity;
import com.anti289.wcd.dto.User;
import com.anti289.wcd.endpoint.response.UserResponse;


@Mapper
public interface UserMapper {

	User map(UserEntity userEntity);

	UserResponse map(User user);

	List<UserResponse> map(List<User> all);

}
