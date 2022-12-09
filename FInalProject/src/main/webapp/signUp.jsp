<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
    <head>
        <title>SIGNUP</title>
        <link rel="stylesheet" href="css/login2.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
    	<script src="https://malsup.github.io/jquery.form.js"></script> 
    </head>
    <body>
    <form action="insert1.do" method="post" class="signUpForm" name="smf">
    <div style="width:400px; margin:auto; border-radius: 0.5rem;
        box-shadow: 0.2rem 0.30rem 0.2rem -0.12rem rgba(0, 0, 0, 0.45);">
			<div style="text-align : center;">
				<a href="index.do"><img src="imgs/banner.png"></a>
				<h2 style="font-size: 18px; color: #6A24FE;">회원 가입</h2>
            </div>
       
        <div>
            <b class="input_label">UserID</b><br>
            <input class="text" type="text" name="id" placeholder="아이디"><br><br>
        </div>
        
				<div>
					<b class="input_label">닉네임</b><br>
					<input class="text" type="text" name="nickname" placeholder="닉네임"><br><br>
        </div>
        <div>
            <b class="input_label">PW</b><br>
            <input class="text" type="password" name="password" id="user_pass" placeholder="패스워드"><br><br>
        </div>
        <div>
            <b class="input_label">PW 확인</b><br>
            <input class="text" type="password" name="PWCheck" id="chpass" placeholder="패스워드확인"><br>
            <font name="check" size="2" color="red"></font> 
						
						<br><br>
        </div>
        <div>
					<h3> 관심있는 언어</h3>
						<label>Java<input type="checkbox" /></label>
						<label>JavaScript<input type="checkbox" /></label>
						<label>Python<input type="checkbox" /></label><br>
						<label>C<input type="checkbox" /></label>
						<label>C#<input type="checkbox" /></label>
						<label>기타<input type="checkbox" /></label><br>			
        </div>
        <div>
						<br>
            <input type="submit" class="sign_up" value="회원 가입" style="margin-bottom: 20px; color: white;"></input>		
        </div>
       </div>
    </form> 
    </body>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	let isPassSame = false;
 $(function(){
  $('#user_pass').keyup(function(){
   $('font[name=check]').text('');
  }); //#user_pass.keyup

  $('#chpass').keyup(function(){
   if($('#user_pass').val()!=$('#chpass').val()){
    $('font[name=check]').text('');
    $('font[name=check]').html("암호틀림");
    isPassSame = false;
   }else{
    $('font[name=check]').text('');
    $('font[name=check]').html("암호맞음");
    isPassSame = true;
   }
  }); //#chpass.keyup
 });
 
 
 var option = {
		    //dataType : 'json', //JSON형태로 전달도 가능합니다.
		    url: "insert.do",
		    success: function(res){
		        //alert(res.msg); //res Object안에 msg에는 결과 메시지가 담겨있습니다.
		    },
		    error: function(res){
		        alert("에러가 발생했습니다.")
		    }
		}
 

 $('.signUpForm').submit(function() { //submit이 발생하면

	 	if(this.id.value == '' ){
	 		this.id.style.borderColor = "red";
	 		return false;
	 	}
	 	
		if(this.nickname.value == '' ){
	 		this.nickname.style.borderColor = "red";
	 		return false;
	 	}
		
		if(this.password.value == '' ){
	 		this.password.style.borderColor = "red";
	 		return false;
	 	}
		if(this.PWCheck.value == '' ){
	 		this.PWCheck.style.borderColor = "red";
	 		return false;
	 	}
		if(!isPassSame){
	 		this.PWCheck.style.borderColor = "red";
	 		return false;
	 	}
		

		$.get("CheckId.do?id="+this.id.value, (data, status)=>{
		    if(data == "1"){
		    	alert("이미 존재하는 아이디 입니다");
		    }else{
		    	let form= document.querySelector(".signUpForm");
		    	form.submit();
		    	
		    }
		    
		 });

		
	    return false; //기본 동작인 submit의 동작을 막아 페이지 reload를 막는다.
	});
 
 var textArr = document.querySelectorAll(".text");
 textArr.forEach(ele => {
	 ele.addEventListener("input",(e)=>{
		 e.target.style.borderColor = "";
	 });
 });
</script>
</html>

