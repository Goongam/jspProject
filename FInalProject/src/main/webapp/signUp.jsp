<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
    <head>
        <title>SIGNUP</title>
        <link rel="stylesheet" href="css/login2.css">
    </head>
    <body>
    <form action="insert.do" method="post">
    <div style="width:400px; margin:auto; border-radius: 0.5rem;
        box-shadow: 0.2rem 0.30rem 0.2rem -0.12rem rgba(0, 0, 0, 0.45);">
			<div style="text-align : center;">
				<img src="imgs/banner.png"><br>
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
            <input class="text" type="password" name="PWCheck" id="chpass" placeholder="패스워드확인">
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
 $(function(){
  $('#user_pass').keyup(function(){
   $('font[name=check]').text('');
  }); //#user_pass.keyup

  $('#chpass').keyup(function(){
   if($('#user_pass').val()!=$('#chpass').val()){
    $('font[name=check]').text('');
    $('font[name=check]').html("암호틀림");
   }else{
    $('font[name=check]').text('');
    $('font[name=check]').html("암호맞음");
   }
  }); //#chpass.keyup
 });
</script>
</html>

