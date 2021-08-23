package com.example.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@Entity //ORM(Obj->테이블로 매핑)User 클래스가 MySQL에 테이블 자동 생
public class Reply {
	
		@Id //Primary key
		//넘버링
		//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 ex)오라클-시퀀스, MySQL-increment~
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id; //시퀀스(오라클), auto-increment
		
		@Column(nullable = false, length = 200)
		private String content;

		@ManyToOne
		@JoinColumn(name="boardId")
		private Board board;
		
		@ManyToOne
		@JoinColumn(name="userId")
		private User user;
		
		@CreationTimestamp
		private Timestamp createDate;
}
