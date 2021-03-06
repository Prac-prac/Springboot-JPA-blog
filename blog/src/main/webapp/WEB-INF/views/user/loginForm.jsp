<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" class="form-control" placeholder="Enter username" name="username" id="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password</label> 
			<input type="password" class="form-control" placeholder="Enter password" name="password" id="pwd">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> 
				<input class="form-check-input" type="checkbox" name="remember"> Remember me</label>
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>


</div>

 <!-- <script src="../js/user.js"></script>  자바스크립트 사용 안하고 form 로그인 할것   -->
<%@ include file="../layout/footer.jsp"%>
