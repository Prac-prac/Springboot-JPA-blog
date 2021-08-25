package com.example.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.model.User;

//JpaRepository CRUD 다 들고 있음
//DAO
//Bean으로 등록 되나요?=스프링IoC에서 객체를 갖고 있나?
//자동으로 bean 등록됨
//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username=1?;
	Optional<User> findByUsername(String username);
	
	
	//JPA Naming 쿼리
	//SELECT * FROM user WHERE username=?1 AND password=?2;
	//jpa있는거 x. 내가 findby이용해만드는거
	//User findByUsernameAndPassword(String username, String password);

/* 같은 방법2
//	UserRepository.login();
	@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2;", nativeQuery = true)
	User login(String username, String password);
	*/
}
