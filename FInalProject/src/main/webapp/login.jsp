<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<link rel="stylesheet" type="text/css" href="css/login3.css"> 
<body style="text-align:center;">
    <div class="login-wrapper" style="border-radius: 0.5rem;
        box-shadow: 0.2rem 0.30rem 0.2rem -0.12rem rgba(0, 0, 0, 0.45); margin: 200px auto;">
        <img src="imgs/banner.png"><h2>Login</h2>
        <form method="post" action="서버의url" id="login-form">
            <input type="text" name="userName" placeholder="ID">
            <input type="password" name="userPassword" placeholder="Password">
            <label for="remember-check">
                <input type="checkbox" id="remember-check">아이디 / 비밀번호 저장하기
            </label>
            <input type="submit" value="로그인">
            <input type="submit" value="회원가입">
        </form>
    </div>
</body>
