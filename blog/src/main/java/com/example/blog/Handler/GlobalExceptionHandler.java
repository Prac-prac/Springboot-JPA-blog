package com.example.blog.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.dto.ResponseDto;

@ControllerAdvice //Exception 발생 시 이 클래스로 옴
@RestController
public class GlobalExceptionHandler {

	//받을 Exception
		@ExceptionHandler(value=Exception.class)
		public ResponseDto<String> handlerException(Exception e) {
			return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()); 
		}

/*
  	//받을 Exception
	@ExceptionHandler(value=IllegalArgumentException.class)
	public String handlerArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
*/
}
