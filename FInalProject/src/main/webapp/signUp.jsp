<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
    <head>
        <title>SIGNUP</title>
        <link rel="stylesheet" href="css/login2.css">
    </head>
    <body>
    <form action="insert.do" method="post" class="signUpForm">
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
 
 function preventBlink(e, element){
	e.preventDefault();
	element.style.borderColor = "red";
 }
 
 var f = document.querySelector(".signUpForm");
 f.addEventListener("submit" , async function(e) {

 	if(e.target.id.value == '' ) 
 		preventBlink(e, e.target.id);
 	
 	
	if(e.target.nickname.value == '' ) 
		preventBlink(e, e.target.nickname);
	
	
	if(e.target.password.value == '' ) 
		preventBlink(e, e.target.password);
	
	if(e.target.PWCheck.value == '' ) 
		preventBlink(e, e.target.PWCheck);
	
	if(!isPassSame){ //비밀번호 체크
		e.preventDefault();
		e.target.PWCheck.style.borderColor = "red";
	}
	
	const loginCheck = await fetch("CheckId.do?id="+e.target.id.value).then(e=>e.text());
	console.log(loginCheck);
	if(loginCheck == "1"){
		e.preventDefault();
		e.target.id.style.borderColor = "red";
		alert("이미 존재하는 아이디 입니다");
	}
 });
 
 var textArr = document.querySelectorAll(".text");
 textArr.forEach(ele => {
	 ele.addEventListener("input",(e)=>{
		 e.target.style.borderColor = "";
	 });
 });
</script>
</html>

