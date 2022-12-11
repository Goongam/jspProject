
<%@page import="java.sql.Timestamp"%>
<%@page import="com.dm.common.AnswerDAO"%>
<%@page import="com.dm.common.StringUtil"%>
<%@page import="com.dm.view.user.Paging"%>
<%@page import="com.dm.common.TimeDiff"%>
<%@page import="com.dm.common.QuestionDTO"%>
<%@page import="com.dm.common.AnswerDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% ArrayList<String> fav = (ArrayList<String>)session.getAttribute("fav"); %>
<% ArrayList<QuestionDTO> qInfoList = (ArrayList<QuestionDTO>)session.getAttribute("qInfo"); %>
<% ArrayList<AnswerDTO> aInfoList = (ArrayList<AnswerDTO>)session.getAttribute("aInfo"); %>
<%
	Timestamp registerDate = (Timestamp)session.getAttribute("registerDate");
	TimeDiff timediff = new TimeDiff();
%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>

<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/index1.css">
<link rel="stylesheet" type="text/css" href="css/module.css">
<link rel="stylesheet" type="text/css" href="css/myPage.css">



<body>

	<jsp:include page="module/header.jsp"></jsp:include>

	<div class="container">
		<div class="section" style="display:block; margin: 0 auto;">
					<div class="qlist_top" style="width: 900px; margin: 10 auto;">
						<%
						out.println(session.getAttribute("nickName"));
						%>
						님의 마이페이지
					</div>
					<div
						style="width: 900px; background-color: lightgray; height: 400px; margin: 10 auto; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45); overflow: auto;">
						<div class="imgDiv">
							<img alt="프로필 이미지" src="<%=session.getAttribute("profileImg")%>">
						</div>
						<div style="background-color: rgb(209,233,255); width: 60%; height: 150px; margin-top: 40px; margin-left: 40px; float: left; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
						<h3 style="font-weight: bold; margin-top: 10px; ">자기소개</h3><br>
							<% if(session.getAttribute("introduce") != null){
							out.println(session.getAttribute("introduce")); 
							}
							else{
								out.println("아직 자기소개가 존재하지 않습니다!");
							}
							
							%>
							
							</div>
							<a href="infoChange.jsp">
						<input type="submit" value="정보수정"
							style="float: right; margin-top: 10px; margin-right: 10px; font-size: 15px; background-color: rgb(209,233,255); color: white; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45); color: black;"></input>
							</a>
						<div class="imgDiv"
							style=" width: 15%; height: 150px; margin-top: 40px; margin-left: 40px; float: left; overflow: visible; ">
							<%
							if((Integer)(session.getAttribute("vote")) < 10){
								%> <img alt="" src="imgs/badge_1.png" style="width: 100%; height: 100%; overflow: visible;"> <%
							}else if((Integer)(session.getAttribute("vote")) < 20){
								%> <img alt="" src="imgs/badge_2.png" style="width: 100%; height: 100%; overflow: visible;"> <%
							}else{
								%> <img alt="" src="imgs/badge_3.png" style="width: 100%; height: 100%; overflow: visible;"> <%
							}
							%>
							</div>
						<div
							style="background-color: rgb(209,233,255); width: 60%; height: 150px; margin-top: 40px; margin-left: 40px; float: left; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
							<h3 style="font-weight: bold;">
								<%
								out.println(session.getAttribute("nickName"));
								%>
								님의 관심 카테고리
							</h3>
							<br>
							<%
							out.println(session.getAttribute("nickName"));
							%>
							님의 관심 카테고리는
							<%
							for(int i=0; i< fav.size(); i++  ){
								%><button type="button" onclick="location.href='Search.do?category=<%=fav.get(i) %>'"style="font-weight: bold; background-color: lightblue; border: 0 solid; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);" >
								<% out.println(fav.get(i));%></button> <% 
							}
							%>
							입니다.
						</div>
					</div>
					<div
						style="width: 900px; background-color: lightgray; height: 200px; margin: 10 auto; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45); overflow: auto;">
						<div
							style="background-color: rgb(209,233,255); width: 40%; height: 70px; float: left; margin-top: 20px; margin-left: 20px; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
							<h2 style="font-weight: bold;">가입한지 <%=timediff.getDateDiff(registerDate.getTime())%> 일째 입니다.</h2>
						</div>
						<div
							style="background-color: rgb(209,233,255); width: 40%; height: 70px; float: left; margin-top: 20px; margin-left: 20px; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
							<h2 style="font-weight: bold;">받은 추천 횟수 : <% out.println(session.getAttribute("vote")); %></h2>
						</div>
						<div
							style="background-color: rgb(209,233,255); width: 40%; height: 70px; float: left; margin-top: 20px; margin-left: 20px; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
							<h2 style="font-weight: bold;">작성한 질문 수 : <% out.println(session.getAttribute("questionCount"));%></h2>
						</div>
						<div
							style="background-color: rgb(209,233,255); width: 40%; height: 70px; float: left; margin-top: 20px; margin-left: 20px; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
							<h2 style="font-weight: bold;">작성한 답변 수 : <% out.println(session.getAttribute("answerCount"));%></h2>
						</div>
						<div class="paging_box">
							<div class="paging_warp"></div>
						</div>
					</div>
				</div>
				<div style="width:900px; display: flex; margin: 10 auto;" >
				<div style="width: 445px; background-color: lightgray; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
						<h3 style="font-weight: bold; margin: 10px; margin-top: 10px;">   <% out.println(session.getAttribute("nickName")); %> 님이 작성한 질문 목록</h3>
						<% 
						for(int i=0; i< qInfoList.size() ; i++){
							 %><a style="color: purple;" href="Question.do?qustionid=<%=qInfoList.get(i).getQuestion_id()%>"><h4 style="font-weight: bold; margin: 10px;"> <% out.println(qInfoList.get(i).getQuestion_title());%></h4></a> <%
							
						}
						
						%>
						</div>
				<div style="width: 445px; background-color: lightgray; margin-left:10px; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45);">
						<h3 style="font-weight: bold; margin: 10px; margin-top: 10px;">   <% out.println(session.getAttribute("nickName")); %> 님이 작성한 답변 목록</h3>
						<% for(int i=0; i< aInfoList.size(); i++){
							System.out.println(aInfoList.get(i).getTitle());
							if(aInfoList.get(i).getQuestion_id() != 0){
							 %><a style="color: purple;" href="Question.do?qustionid=<%= aInfoList.get(i).getQuestion_id() %>"><h4 style="font-weight: bold; margin: 10px;"> <% out.println(aInfoList.get(i).getTitle());%></h4></a> <%
							}} %>
						</div>	
					</div>	
				</div>  
			<%-- <jsp:include page="module/myPageRight.jsp"/> --%>
	<jsp:include page="module/footer.jsp"></jsp:include>
</body>
</html>

							