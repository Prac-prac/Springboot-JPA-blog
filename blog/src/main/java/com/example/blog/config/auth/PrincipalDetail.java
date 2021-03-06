package com.example.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.blog.model.User;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
//UserDetails타입의 오브젝트를 스프링 시큐리티의 고유한 세션저장소에 저장함
public class PrincipalDetail implements UserDetails{
	private User user; //컴포지션:객체를 품고 있(PrincipalDetail이 User 품고 있)

	public PrincipalDetail(User user) {
		this.user=user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	//false되면 로그인 안됨
	//계정이 만료되지 않았는지 리턴
	@Override
	public boolean isAccountNonExpired() {
		return true; //만료 안됨
	}

	//계정이 잠겨있지 않았는지 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true; //잠기지 않음
	}

	//비밀번호가 만료되지 않았는지 리턴
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화(사용가능)인지
	@Override
	public boolean isEnabled() {
		return true; //활성화
	}
	
	//계정의 권한 목록 리턴(권한이 여러 개 있으면 루프(for문)를 돌림. 여기선 하나.)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		//매개변수로 GrantedAuthority타입을 줘야해서 
		//GrantedAuthority -> 인터페이스 -> 익명 클래스 만들어짐 -> 추상메서드 오버라이드
		/* 자바에서는 메서드에 객체 못 넘김
		collectors.add(new GrantedAuthority() {

			//스프링 롤 받을 때 앞에 ROLE_ 넣어줘야 - > 안 쓰면 role이라고 인식x
			@Override
			public String getAuthority() {
				return "ROLE_"+user.getRole();
			}
			
		});
		*/
		//GrantedAuthority 메서드 하나 들고 있 -> 람다식으로
		//객체 안 감싸고 함수 바로 넣으려면
		collectors.add(()->{return "ROLE_"+user.getRole();});
		return collectors;
	}


}
