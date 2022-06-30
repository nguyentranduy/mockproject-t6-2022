package com.mockproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mockproject.constant.SessionConstant;
import com.mockproject.entity.Products;
import com.mockproject.entity.Users;
import com.mockproject.service.ProductsService;
import com.mockproject.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductsService productsService;
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping(value = {"/", ""})
	public String doGetRedirectIndex() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String doGetIndex(Model model) {
		List<Products> products = productsService.findAll();
		model.addAttribute("products", products);
		return "user/index";
	}
	
	@GetMapping("/login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new Users());
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute(SessionConstant.CURRENT_USER);
		return "redirect:/index";
	}
	
	@PostMapping("/login")
	public String doPostLogin(@ModelAttribute("userRequest") Users userRequest, HttpSession session) {
		Users userResponse = usersService.doLogin(userRequest);
		if (ObjectUtils.isNotEmpty(userResponse)) {
			session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
			return "redirect:/index";
		}
		return "redirect:/login";
	}
}
