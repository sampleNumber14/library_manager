package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// http://localhost:8080/loginForm ログインのフォーム

@Controller
public class LoginController {
	
	@GetMapping("/loginForm")
	public String getLogin(){
		return "loginForm";
	}

}
