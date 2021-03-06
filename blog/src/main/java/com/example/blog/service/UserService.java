package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder; //SecurityConfig에서 bean 등록한 객체
	
	@Transactional
	public void 회원가입(User user) {
		//1234 원문
		String rawPassword = user.getPassword(); //회원가입 버튼 누를 때(js에서 /auth/joinProc로 넘겨 UserApiController에서 user 받음. 그리고 service 처리하러 여기로.) 값 넘겨받아 user가 들고 있음 
		//해쉬화
		String enPassword = encoder.encode(rawPassword);
		user.setPassword(enPassword);
		user.setRole(RoleType.USER);
		//	try-catch 의미X. 예외 터지면 GlobalExceptionHandler가 잡기 때문에
		userRepository.save(user);
	}
	
/*	
	//select하는데 트랜잭션 쓰는 이유 : 정합성
	@Transactional(readOnly = true) //select할 때 서비스 시작, 서비스 종료시 트랜잭션 종료
	public User 로그인(User user) {
		//	try-catch 의미X. 예외 터지면 GlobalExceptionHandler가 잡기 때문에
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
*/		
}
