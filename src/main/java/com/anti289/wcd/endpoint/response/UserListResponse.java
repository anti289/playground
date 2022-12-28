package com.anti289.wcd.endpoint.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListResponse {

	private List<UserResponse> users;

}
