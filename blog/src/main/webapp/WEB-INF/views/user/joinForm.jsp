<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
<!--	<form action="/user/join"  method="POST"> 옛날 방식. JS ajax 이용할 것. json으로 데이터 전송-->
	<form>	
		<div class="form-group">
			<label for="username">Username</label> <input type="text" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="pwd">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="pwd">
		</div>
		<div class="form-group">
			<label for="email">Email</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
	</form>
	<!-- button form 안에 있으면 submit. 밖으로 빼준다 -->
		<button id="btn-save" class="btn btn-primary">회원가입완료</button>


</div>

<script src="../js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>
