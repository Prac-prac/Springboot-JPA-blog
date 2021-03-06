package com.example.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
//클래스 테이블화
@Entity //ORM(Obj->테이블로 매핑)User 클래스가 MySQL에 테이블 자동 생성됨
//@DynamicInsert //role @ColumnDefault("'user'") 위해서. insert할 때 null인 필드 제외
public class User {
	
	@Id //Primary key
	//넘버링
	//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 ex)오라클-시퀀스, MySQL-increment~
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //시퀀스(오라클), auto-increment
	
	@Column(nullable = false, length = 30, unique=true)
	private String username; //아이디
	
	@Column(nullable = false, length = 100) //해쉬로 변경해 비밀번호 암호화할 것
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'") //문자라는 것 알려주기 위해 ' '
	//DB는 RoleType이 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum 데이터에 도메인 만들어줄 수
	// Enum을 쓰는게 좋다. // ADMIN, USER -> 도메인(프로그래밍에서 도메인은 범위를 의미)
	
	@CreationTimestamp //시간 자동 입력
	private Timestamp createDate;
}
