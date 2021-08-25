package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//여기가 스프링 시큐리티 로그인 페이지 커스터미아징
//아래 3개의 어노테이션 세트
//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@Configuration //빈 등록(IoC관리)
@EnableWebSecurity //시큐리티 필터 등록 = 이미 활성화된 스프링 시큐리티(시큐리티가 모든 request 가로챔)에 설정을 걸어주겠다 
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근 시 권한 및 인증을 미리 체크. 요청 수행 후 권한 및 인증 시큐리티 동작x.
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	//.encode() 해쉬로 암호화해 줌
	@Bean //return값 객체인  BCryptPasswordEncoder 스프링이 관리해 IoC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf 토큰 비활성화(테스트 시 걸어두는 게 좋음)
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") //폴더 허용해줌
				.permitAll() //위 경로로 들어오는 건 모두 허용
				.anyRequest()
				.authenticated() //나머지는 인증이 되어야 한다
		//인증 요청(auth/**빼고는 다)
			.and()
				.formLogin()
				.loginPage("/auth/loginForm"); //인증 필요한 것으로 요청되면 얘가 자동으로 뜸

	}

}