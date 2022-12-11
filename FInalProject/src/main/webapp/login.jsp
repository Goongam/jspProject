<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="css/login3.css">
<%
String idValue = "";
String pwValue = "";
String checked = "";

if (session.getAttribute("Checked") != null) {
	idValue = (String) session.getAttribute("idValue");
	pwValue = (String) session.getAttribute("pwValue");
	checked = (String) session.getAttribute("Checked");
}

String isFail = request.getParameter("loginfail");
%>
<body style="text-align: center;">
	<div class="login-wrapper"
		style="border-radius: 0.5rem; box-shadow: 0.2rem 0.30rem 0.2rem -0.12rem rgba(0, 0, 0, 0.45); margin: 200px auto;">
		<a href="index.do"><img src="imgs/banner.png"></a>

		<h2>Login</h2>
		<%
		if (isFail != null)
			out.print("아이디 비밀번호 다시 입력");
		%>
		<form method="post" action="login.do" id="login-form">
			<input type="text" name="id" placeholder="ID" value=<%=idValue%>>
			<input type="password" name="password" placeholder="Password"
				value=<%=pwValue%>> <label for="remember-check"> <input
				type="checkbox" name="infosave" id="remember-check" <%=checked%>>아이디
				/ 비밀번호 저장하기
			</label> <input type="submit" value="로그인">
		</form>
		<div id="sign">
			<a href="signUp.jsp" style="">회원가입</a>
		</div>
	</div>
</body>
