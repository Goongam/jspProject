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

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/module.css"> 
<link rel="stylesheet" type="text/css" href="css/Question.css"> 


</head>

<%

	QuestionDTO question = (QuestionDTO)session.getAttribute("questionData");
	ArrayList<AnswerDTO> answers = (ArrayList<AnswerDTO>)session.getAttribute("answerData");
	TimeDiff timediff = new TimeDiff();

%>

<body>

	<jsp:include page="module/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="section">
			<div class="content">
				<div class="question">
					<div class="question_inner">
						<div class="question_member_info">
							<img class="profile_img" src="imgs/todayQ.png">
							<span><%= question.getMemeber_id() %></span>
							<span><%= timediff.getTimeDiff(question.getEdit_time()) %></span>
							<span><%= question.getViews() %></span>
                        </div>
						<div><h1 class="question_title"> <%= question.getQuestion_title() %> </h1></div>
						<div class="question_content"> <%= question.getQuestion_contnet() %> </div>
					</div>
				</div>
				<div class="answer_count">답변 <%=answers.size() %>개</div>
				<div class="answer">
					<%
						for(AnswerDTO answer : answers){
							%>
							<div class="answer_inner">
								<div class="answer_info_warp">
									<div class="question_member_info">
										<img class="profile_img" src="imgs/todayQ.png">
										<span><%= answer.getMember_id() %></span>
										<span><%= timediff.getTimeDiff(answer.getEdit_time()) %></span>
			                        </div>
			                        <div class="answer_vote">
			                        	<button onclick="voteUp(<%=answer.getId()%>)">&#128077;</button>
			                        	<span class="vote_<%=answer.getId()%>"><%=answer.getVote() %></span>
			                        	<button onclick="voteDown(<%=answer.getId()%>)">&#128078;</button>
			                        </div>
								</div>
								<div><h1 class="answer_title"> <%= answer.getTitle() %> </h1></div>
								<div class="answer_content"> <%= answer.getContent() %> </div>
							</div>
							<%
						}
					%>
				</div>
	
			</div>
			<jsp:include page="module/rightwarp.jsp"></jsp:include>
			
		</div>
	
	</div>
	
	
	<jsp:include page="module/footer.jsp"></jsp:include>
</body>
<script>
	async function voteUp(id){
		const {voteCount} = await fetch("AnswerVote.do?AnswerId="+id+"&isUp="+1).then(async (res)=>await res.json());
		document.querySelector(".vote_"+id).innerText = voteCount;

	}
	async function voteDown(id){
		const {voteCount} = await fetch("AnswerVote.do?AnswerId="+id+"&isUp="+0).then(async (res)=>await res.json());
		document.querySelector(".vote_"+id).innerText = voteCount;

	}
</script>
</html>