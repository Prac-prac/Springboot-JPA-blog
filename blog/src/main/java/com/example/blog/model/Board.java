package com.example.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량
	private String content; //섬머노트 라이브러리 <html>태그가 섞여 디자인 됨
	
	@ColumnDefault("0")
	private int viewcount;
	
	//private int userId;
	//ORM에서는
	@ManyToOne //User와 연관 관계 설정. Many=Board, One=User 하나의 유저가 여러개 글 작성 
	@JoinColumn(name="userId")//컬럼명 정해줌.
	//외래키되어 User의 기본키 따라감
	private User user;
	
	//JoinColumn 필요 없음. DB에 저장X. 값 하나만 올 수 있는데 리플은 여러개. 1정규화 원자성 무너짐
	//mapperBy : 연관관계의 주인X(FK아님). DB에 컬럼 만들지말것.
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //reply 테이블의 board
	private List<Reply> reply;
	
	@CreationTimestamp //insert,update될 때 자동으로
	private Timestamp createDate;
}
