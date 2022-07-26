package com.mockproject.util;

import javax.servlet.http.HttpSession;

import com.mockproject.constant.SessionConstant;
import com.mockproject.dto.CartDto;
import com.mockproject.entity.Users;

public class SessionUtil {
	
	private SessionUtil() {}
	
	public static CartDto getCurrentCart(HttpSession session) {
		return (CartDto) session.getAttribute(SessionConstant.CURRENT_CART);
	}
	
	public static Users getCurrentUser(HttpSession session) {
		return (Users) session.getAttribute(SessionConstant.CURRENT_USER);
	}
}
