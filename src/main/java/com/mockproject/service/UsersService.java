package com.mockproject.service;

import java.sql.SQLException;

import com.mockproject.entity.Users;

public interface UsersService {

	Users doLogin(Users userRequest);
	Users save(Users user) throws SQLException;
}
