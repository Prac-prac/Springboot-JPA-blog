package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//  인증이 안 된 사용자들이 출입할 수 있는 경로 
//  1. auth/** : 인증이 필요없는 곳에 붙일 것
//  2. /(index.jsp) 
//  3. static이하에 있는 /js/**, /css/**, /image/**


@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
}
