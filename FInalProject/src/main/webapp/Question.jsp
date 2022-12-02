<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.*, com.dm.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="css/module.css"> 
<link rel="stylesheet" type="text/css" href="css/Question.css"> 
</head>

<%

	QuestionDTO question = (QuestionDTO)session.getAttribute("questionData");
	TimeDiff timediff = new TimeDiff();

%>

<body>

	<jsp:include page="module/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="section">
			<div class="content">
				<div class="question">
					<div class="question_inner">
						<div class="question_info">
							<img class="profile_img" src="imgs/todayQ.png">
							<span><%= question.getMemeber_id() %></span>
							<span><%= timediff.getTimeDiff(question.getEdit_time()) %></span>
							<span><%= question.getViews() %></span>
                        </div>
						<div><h1 class="question_title"> <%= question.getQuestion_title() %> </h1></div>
						<div class="question_content"> <%= question.getQuestion_contnet() %> </div>
					</div>
				</div>

	
			</div>
			<jsp:include page="module/rightwarp.jsp"></jsp:include>
			
		</div>
	
	</div>
	
	
	<jsp:include page="module/footer.jsp"></jsp:include>
</body>
</html>