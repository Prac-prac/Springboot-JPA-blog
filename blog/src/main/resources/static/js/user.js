let index = {
	//let _this = this; //fuction(){}으로 하고 싶으면 
	init: function(){
		$("#btn-save").on("click", ()=>{ //코드 줄이기 위해서X. ()=>{} this를 바인딩하기 위해서. function(){}으로 하고 받으면
			this.save(); //this가 window객체를 가리킴
			//fuction(){}으로 하고 싶으면 this 바인딩 해서 _this.save();
		});
		
	//$("#btn-login").on("click", ()=>{ 
	//		this.login(); 
	//	});
	},
	
	
	save: function(){
		//alert('user의 save함수 호출됨');
		//val 찾아서 data 오브젝트에 넣
		let data = {
			username: $("#username").val(),
			password: $("#pwd").val(),
			email: $("#email").val()
		};
		//console.log(data);
		
		
		//ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해
		$.ajax({
			//통신 수행 //회원가입 수행 요청
			type: "POST", //insert니까
			url: "/auth/joinProc",  //UserApiController에서 PostMapping해서 받음
			//data: data -> 위의 Data 그대로 던지면 javascript Object라 자바가 이해X 
			//아래 2개 세트
			data: JSON.stringify(data),
			//POST http body데이터 MIME타입 필요
			contentType: "application/json; charset=utf-8",
			//요청을 서버로 해서 응답 왔을 때 대게 기본적으로 String(Buffer로 오기 때문)
			//근데 생김새가 json이라면 js Object로 변경해줌. 그리고 함수 매개변수로 전달
			dataType: "json" 
			//응답의 결과 함수 매개변수로
			//js Obj로 변환해 응답
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			console.log(resp);
			//location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	/*
		login: function(){
		//alert('user의 save함수 호출됨');
		//val 찾아서 data 오브젝트에 넣
		let data = {
			username: $("#username").val(),
			password: $("#pwd").val()
		};
		//console.log(data);
		
		
		//ajax 통신을 이용해 3개의 데이터를 json으로 변경하여 insert 요청
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해
		$.ajax({
			//통신 수행 //회원가입 수행 요청
			type: "POST", 
			url: "/api/user/login", 
			//data: data -> 위의 Data 그대로 던지면 javascript Object라 자바가 이해X 
			data: JSON.stringify(data),
			//POST http body데이터 MIME타입 필요
			contentType: "application/json; charset=utf-8",
			//요청을 서버로 해서 응답 왔을 때 대게 기본적으로 String(Buffer로 오기 때문)
			//근데 생김새가 json이라면 js Object로 변경해줌. 그리고 함수 매개변수로 전달
			dataType: "json" 
			//응답의 결과 함수 매개변수로
			//js Obj로 변환해 응답
		}).done(function(resp){
			 if (resp.status === 404) throw new Error('Cannot find principal');
			alert("로그인이 완료되었습니다.");
			console.log(resp);
			//location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	} */
}

index.init();