package com.example.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;


@RestController
public class DummyControllerTest {
	
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//http의 body에 username, password, email 데이터 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id:"+user.getId());
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		System.out.println("role:"+user.getRole());
		System.out.println("creatDate:"+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
	
	//{id} 주소로 파라미터 전달 받을 수
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//- Supplier 인터페이스 -> 인터페이스 new하려면 익명객체로 생성 후 오버라이딩
		//-람다식
//	    	User user = userRepository.findById(id).orElseThrow(()->{
//	   			return new IllegalArgumentException("해당 사용자는 없습니다.");
//	   	     });
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id:"+id);
			}
			
		});
		//자바 Object 웹브라우저가 이해 못함. json으로 변환해 주어야.
		//원래 Gson라이브러리로.
		//스프링부트는 MessageConvertor가 응답 시에 return 객체를 json으로 변환해 브라우저에게
		//Jackson라이브러리 호출해서.
		return user;
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	
	//한 페이지당 2건의 데이터 리턴 받아 봄
	//뒤에 ?page=숫자 해서 페이지 이동
	 @GetMapping("/dummy/user")
	 public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		 Page<User> pagingUser= userRepository.findAll(pageable);
		 //Page로 리턴 받으면 이런 내장 메소드 사용 가능. getContent() 따로 끊어서 List로 받기
//		 if(pagingUser.isFirst()) {
			 
//		 }
		 
		 List<User> users = pagingUser.getContent();
		 return users;
	 }
	 
	 
	 
	 //email, password만 update
	 //@Transactional : save()하지 않아도 값 변경하면 update되게 함
	 @Transactional //함수 종료 시 자동 commit 됨
	 @PutMapping("/dummy/user/{id}")
	 public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

		 //DB findById로 select해서 user로 받고 
		 User user = userRepository.findById(id).orElseThrow(()->{
			 return new IllegalArgumentException("수정에 실패하였습니다.");
		 });
		 //값 변경 후 @Transcational 걸면 update -> "더티체킹": 영속성 컨텍스트에서 "변경감지" 후 DB 수정
		 user.setPassword(requestUser.getPassword());
		 user.setEmail(requestUser.getEmail());
		 //userRepository.save(requestUser); //save insert할 때 주로 사용. update때 X
		 return null;
	 }
	 
	 
	 
	 @DeleteMapping("/dummy/user/{id}")
	 public String delete(@PathVariable int id) {
		 try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		 return "삭제되었습니다. id : "+id;
	 }
}
