<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.* , com.dm.common.*"%>

<jsp:useBean class="com.dm.common.RegisterDAO" id="regMgr2"
	scope="session" />
<%
ArrayList<RegisterDTO> mlist = (ArrayList<RegisterDTO>) session.getAttribute("vlist");
ArrayList<QuestionDTO> plist = (ArrayList<QuestionDTO>) session.getAttribute("plist");
ArrayList<CategoryDTO> clist = (ArrayList<CategoryDTO>) session.getAttribute("clist");
ArrayList<ReportDTO> reportList = (ArrayList<ReportDTO>)session.getAttribute("reportList");
HashMap<Integer,String> ReportTitleMap = (HashMap<Integer,String>)session.getAttribute("ReportTitleMap");
%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>

<link rel="stylesheet" type="text/css" href="css/adminPage.css">
<link rel="stylesheet" type="text/css" href="css/module.css">


<body>
	<jsp:include page="module/header.jsp"></jsp:include>
	<div class="container">
			<input type="submit" value="회원 목록 보기" id="memBtn" style="margin-left: 30px;"> 
			<input type="submit" value="글 목록 보기" id="queBtn">
			<input type="submit" value="카테고리 목록 보기" id="catBtn">
		<div class="admin_content">
			<div class="table_wrap">
				<table width="100%" align="center" id="memberList">
					<tr>
						<td align="center" bgcolor="">
							<table width="95%" align="center" bgcolor="lightblue" border="1">
								<tr bgcolor="">
									<td align="center"><font color="">회원아이디</font></td>
									<td align="center"><font color="">회원이름</font></td>
									<td align="center"><font color="">패스워드</font></td>
								</tr>
								<%
								for (int i = 0; i < mlist.size(); i++) {
									RegisterDTO regBean = (RegisterDTO) mlist.get(i);
								%>
								<tr>
									<td align="center"><%=regBean.getId()%></td>
									<td align="center"><%=regBean.getNickname()%></td>
									<td align="center"><%=regBean.getPassword()%></td>
									<% 
									if(regBean.getId().equals("admin")){
										%><td align="center">관리자</td><%
									}
									else{
									 %>
									 <td align="center"><a href="delete_m.do?delM=<%=regBean.getId()%>">삭제하기</a></td>
									 <%
									}
									%>
								</tr>
								<%
								
								}
								%>
							</table>
						</td>
					</tr>
				</table>
				
				<table width="100%" align="center" id="questionList">
					<tr>
						<td align="center" bgcolor="">
							<table width="95%" align="center" bgcolor="lightblue" border="1">
								<tr bgcolor="">
									<td align="center"><font color="">글 id</font></td>
									<td align="center"><font color="">글 제목</font></td>
									<td align="center"><font color="">작성자 id</font></td>
									<td align="center"><font color="">카테고리</font></td>
								</tr>
								<%
								for (int i = 0; i < plist.size(); i++) {
									QuestionDTO regBean2 = (QuestionDTO) plist.get(i);
								%>
								<tr>
									<td align="center"><%=regBean2.getQuestion_id()%></td>
									<td align="center"><%=regBean2.getQuestion_title()%></td>
									<td align="center"><%=regBean2.getMemeber_id()%>
									<td align="center"><%=regBean2.getcategory()%></td>
									<td align="center"><a href="delete_q.do?delQ=<%=regBean2.getQuestion_id() %>">삭제하기</a></td>
								</tr>
								<%
								}
								%>
							</table>
						</td>
					</tr>
				</table>
				<div id="CategoryList">
				<form  method="post" action="insertc.do" >
				<input type="text" name="category_n" placeholder="추가할 카테고리명을 입력해 주세요" style="width: 600px; margin-top:10px; margin-left: 30px;"></input>
				<input type="submit" value="추가하기"></input>
				</form>
				<table width="100%" align="center">
					<tr>
						<td align="center" bgcolor="">
							<table width="95%" align="center" bgcolor="lightblue" border="1">
								<tr bgcolor="">
									<td align="center"><font color="">카테고리 이름</font></td>
								</tr>
								<%
								for (int i = 0; i < clist.size(); i++) {
									CategoryDTO regBean3 = (CategoryDTO) clist.get(i);
									%>
								<tr>
									<td align="center"><%=regBean3.getCategory_name()%></td>
									<td align="center"><a href="delete_c.do?delC=<%=regBean3.getCategory_name()%>">삭제하기</a></td>
								</tr>
								<%
								}
								%>
							</table>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div class="report_list">
				<div class="report_text">신고내역</div>
				<%
					for(ReportDTO report : reportList){
				%>
					<div class="report_article">
						<div><a href="<%
							if(report.getType().equals("q")){ //question
								out.print("Question.do?qustionid="+report.getArticle_id());
							}else{
								out.print("Answer.do?answerid="+report.getArticle_id());
							}
						%>"><%=report.getTitle()%></a></div>
						<div><%=report.getMsg() %></div>
						<div class="delete_report"><a href="ReportDelete.do?id=<%=report.getId()%>">🗑</a></div>
					</div>
				<%
					}
				%>
				
				
			</div>
		</div>
		
	</div>
	
	<div style="margin-bottom: 30px;"></div>
	<jsp:include page="module/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	const ml = document.querySelector("#memberList");
	const ql = document.querySelector("#questionList");
	const cl = document.querySelector("#CategoryList");
	const mb = document.querySelector("#memBtn");
	const qb = document.querySelector("#queBtn");
	const cb = document.querySelector("#catBtn");
	ql.style.display = "none";
	cl.style.display = "none";
	ml.style.display = "block";
	mb.addEventListener("click", mlon)
	qb.addEventListener("click", qlon)
	cb.addEventListener("click", clon)

	function mlon() {
		ml.style.display = "block";
		ql.style.display = "none";
		cl.style.display = "none";
	}

	function qlon() {
		ql.style.display = "block";
		ml.style.display = "none";
		cl.style.display = "none";
	}
	
	function clon() {
		cl.style.display = "block"
		ql.style.display = "none";
		ml.style.display = "none";
	}
</script>
</html>

