<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String i = request.getParameter("UserID");
String p = request.getParameter("PW");
String c = request.getParameter("C");
if (i.equals("dong") && p.equals("12")) {
	//성공
	session.setAttribute("memberId", "ok");
	session.setAttribute("idName", i);

	if (c != null) {
		session.setAttribute("checked", "on");

	} else {
		session.removeAttribute("checked");
	}
	response.sendRedirect(request.getHeader("referer"));

} else {
	//실패

	response.sendRedirect(request.getHeader("referer"));

}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>