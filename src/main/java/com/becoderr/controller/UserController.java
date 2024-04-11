package com.becoderr.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.becoderr.entity.User;
import com.becoderr.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository	userRepository;
	
	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if(p!= null) {
		String email = p.getName();
		User user =	userRepository.findByEmail(email);
		
		m.addAttribute("user",user);
		
		}
	}
	
	@GetMapping("/profile")
	public String profile() {
		return "profile";
	}
}
