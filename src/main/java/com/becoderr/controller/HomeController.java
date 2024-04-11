package com.becoderr.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.becoderr.entity.User;
import com.becoderr.repository.UserRepository;
import com.becoderr.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/signIn")
	public String signIn() {
		return "login";
	}

	//	@GetMapping("/user/profile")
	//	public String profile(Principal p, Model m) {
	//		String email = p.getName();
	//		User user =	userRepository.findByEmail(email);
	//		m.addAttribute("user",user);
	//		return "profile";
	//	}
	//
	//	@GetMapping("/user/home")
	//	public String home() {
	//		return "home";
	//	}

	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		// System.out.println(url);        //     http://localhost:8080/saveUser

		url = url.replace(request.getServletPath(), "");
		// System.out.println(url);       //      http://localhost:8080/verify?code=43545455


		User saveUse = userService.saveUser(user, url); 
		
		if(saveUse!=null) {
			System.out.println("save successfully"); 
			session.setAttribute("msg", "Register Succsessfully");

		}else { // System.out.println("error"); 
			session.setAttribute("msg",  "Something went wrong"); 
		}
		return "redirect:/register";
	}
	
	
	@GetMapping("/verify")
	public String verifyAccount(@Param("code") String code, Model m) {
		boolean f = userService.verifyAccount(code);
		if(f) {
			m.addAttribute("msg", "Successfully your account is verified");
		}else {
			m.addAttribute("msg", " May be your verifcation code is incorrect or already verified"); 
		}
		return "message";
	}
	

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			User user = userRepository.findByEmail(email);

			m.addAttribute("user", user);
		}
	}
}
