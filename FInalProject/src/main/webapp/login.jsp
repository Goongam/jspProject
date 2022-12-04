<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/login3.css"> 
<%

String idValue = "";
String pwValue = "";

String checked = (String)session.getAttribute("checked");
if(checked != null){
	idValue = (String)session.getAttribute("idValue");
	pwValue = (String)session.getAttribute("pwValue");
}
%>
<body style="text-align:center;">
    <div class="login-wrapper" style="border-radius: 0.5rem;
        box-shadow: 0.2rem 0.30rem 0.2rem -0.12rem rgba(0, 0, 0, 0.45); margin: 200px auto;">
        <img src="imgs/banner.png"><h2>Login</h2>
        <form method="post" action="login.do" id="login-form">
            <input type="text" name="id" placeholder="ID" value=<%= idValue %>>
            <input type="password" name="password" placeholder="Password" value=<%= pwValue %>>
            <label for="remember-check">
                <input type="checkbox" name="infosave" id="remember-check" <%= checked %>>아이디 / 비밀번호 저장하기
            </label>
            <input type="submit" value="로그인">
        </form>
        <div id="sign">
        <a href="signUp.jsp" style="">회원가입</a>
        </div>
    </div>
</body>
