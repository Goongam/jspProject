<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dm.common.AnswerDAO"%>
<%@page import="com.dm.common.StringUtil"%>
<%@page import="com.dm.view.user.Paging"%>
<%@page import="com.dm.common.TimeDiff"%>
<%@page import="com.dm.common.QuestionDTO"%>
<%@page import="java.util.ArrayList"%>
    
<%
	AnswerDAO answerDAO = new AnswerDAO();

	TimeDiff timediff = new TimeDiff();
	
	String id = request.getParameter("id");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String category = request.getParameter("category");
	String edit_time = request.getParameter("Edit_time");
	String views = request.getParameter("views");
	
	long edit_time_long = Long.parseLong(edit_time);
	
%>
<div class="question_item">
    <h3 class="question_item_title"><a href="Question.do?qustionid=<%= id%>"><%= title.replaceAll("<[^>]*>", " ") %></a></h3>
    <div class="question_item_content"><%=content.replaceAll("<[^>]*>", " ") %></div>
    <div class="question_item_sub">
        <div class="question_item_sub_category">
        	<a href="Search.do?category=<%= StringUtil.getURLEscapeCode(category) %>"><%=category%></a>
        </div>
        <ul class="question_item_sub_ul">
            <li><%= timediff.getTimeDiff(edit_time_long) %></li>
            <li>💬 <span class="answer_count_<%=id%>"><%= answerDAO.SelectAnswerCount(id) %></span></li>
            <li>👁️ <%= views %></li>            
        </ul>
    </div>
</div>

<script>
	async function getAnswerCount(){
		const resCount = await fetch("AnswerCount.do?qid="+<%=id%>);
		const {count} = await resCount.json();
		console.log("<%=id%>:"+count);
		document.querySelector(".answer_count_<%=id%>").innerText = count;
	}
	getAnswerCount();
	
	//AnswerCount.do?qid=8
</script>