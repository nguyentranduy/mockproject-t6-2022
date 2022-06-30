package com.mockproject.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mockproject.entity.Users;
import com.mockproject.repository.UsersRepo;
import com.mockproject.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Autowired
	private UsersRepo repo;

	@Override
	public Users doLogin(Users userRequest) {
		Users userResponse = repo.findByUsername(userRequest.getUsername());
		
		if (ObjectUtils.isNotEmpty(userResponse)) {
			boolean checkPassword = bcrypt.matches(userRequest.getHashPassword(), userResponse.getHashPassword());
			return checkPassword ? userResponse : null;
		}
		
		return null;
	}
}
