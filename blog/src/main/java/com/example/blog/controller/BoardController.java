package com.example.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	//붙일 때랑 안 붙일 때 /
	@GetMapping({"","/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) { //컨트롤러에서 세션 어떻게 찾는지?
		//prefix,suffix로 /WEB-INF/view/index.jsp
		System.out.println("로그인 사용자 아이디: "+principal.getUsername());
		return "index";
	}
}
