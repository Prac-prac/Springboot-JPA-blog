package com.example.blog.config.auth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	//스프링이 로그인 요청을 가로챌 때 username, password 변수 2개를 가로채서
	//password 처리는 알아서
	//username은 DB에 있는지 확인
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username DB에 있으면 User 객체 리턴
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자는 없습니다."+username);
				});
		return new PrincipalDetail(principal); //시큐리티 세션에 유저정보 저장됨. 타입이 UserDetails.
		//이거 안하면 id:user 비밀번호:콘솔창
	}

}
