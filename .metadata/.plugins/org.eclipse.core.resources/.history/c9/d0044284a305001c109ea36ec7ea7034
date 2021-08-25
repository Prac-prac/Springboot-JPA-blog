package com.example.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//붙일 때랑 안 붙일 때 /
	@GetMapping({"","/"})
	public String index() {
		//prefix,suffix로 /WEB-INF/view/index.jsp
		return "index";
	}
}
