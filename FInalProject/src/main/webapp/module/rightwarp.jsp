<%@page import="com.dm.common.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dm.common.CategoryDTO"%>
<%@page import="com.dm.common.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String loginCkeck = (String) session.getAttribute("loginCkeck");
%>
<%
Integer todayQ_id = (Integer) session.getAttribute("todayQ_id");
%>



<div class="warp_right">
	<div id="question_btn" class="border">
		<a href="NewQuestion.do"> 질문하기 </a>
	</div>

	<%
	if (session.getAttribute("isadmin") != null) {
	%>
	<div id="mypage_btn" class="border">

		<a href="list.do"> 관리자 페이지 </a>
	</div>
	<%
	} else {
	%>
	<button onclick="myPagein()"
		style="background-color: rgb(4, 125, 147); width: 230.8px; height: 80px; margin-top: 10px; border: 0 solid; color: white; border-radius: 0.5rem; box-shadow: 0.05rem 0.1rem 0.3rem -0.03rem rgba(0, 0, 0, 0.45); margin-left: 0px;">

		<h3 style="font-weight: bold;">마이페이지</h3>
	</button>
	<%
	}
	%>

	<div class="categorys"></div>
	<%
	if (todayQ_id != null) {
		if(todayQ_id != 0){
			%>
			<a href="Question.do?qustionid=<%=todayQ_id%>" id="today_q_warp"> <img
				src="<%=request.getContextPath()%>/imgs/todayQ.png" id="today_q_img">
				<span id="today_q_inner_text">오늘의 질문입니다</span>
			</a>
			<%
		}
		
	}
	%>

</div>

<script type="text/javascript">

async function myPagein(){
	const loginCheck = "<%=loginCkeck%>";
		if(loginCheck === "null") {
			if(confirm("로그인이 필요한 기능입니다. 로그인 페이지로 이동하시겠습니까?"))
				location.href = "login.jsp";
			return;
	}
		else {
			location.href="getinfo.do"
		}
}

	async function getCategorys(){
		const reslist = await fetch("RightWarp.do");
	    const {catelist} = await reslist.json();
	    
	    const categorysDiv = document.querySelector(".categorys");
	    let innerCategorys = "";
	    catelist.forEach((category)=>{
	    	innerCategorys += "<div><a href='Search.do?category="+category+"'>"+category+"</a></div>"
	    })
	    categorysDiv.innerHTML = innerCategorys;
	}
	getCategorys();
	
</script>