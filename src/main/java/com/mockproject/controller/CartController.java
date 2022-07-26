package com.mockproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mockproject.dto.CartDto;
import com.mockproject.service.CartService;
import com.mockproject.util.SessionUtil;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@GetMapping("")
	public String doGetViewCart() {
		return "user/cart";
	}
	
	@GetMapping("/update")
	public String doGetUpdate(@RequestParam("productId") Long producId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isReplace,
			HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, producId, quantity, isReplace);
		return "user/cart::#viewCartFragment";
	}
}
