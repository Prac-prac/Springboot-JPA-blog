package com.example.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.ResponseDto;
import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.service.UserService;

//앱에서도 쓸 수 있
@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private HttpSession session;

//회원가입 로직	
//Data to Object(Dto) 데이터를 오브젝트로 변환하는 객체
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiContorller:save() 호출됨");
		//실제 DB에 insert하고 return하기
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //user.js로 리턴
	}
	
	// 로그인 전통적인 방식 -> 이제는 시큐리티 이용
	/*
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
		System.out.println("UserApiContorller:login() 호출됨");
		User principal = userService.로그인(user); //principal 접근 주체
		
		if(principal != null) {
			session.setAttribute("principal", principal);
			return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		} else {
			throw new Error("Test exception block");
			
		}
	}
	*/
}
