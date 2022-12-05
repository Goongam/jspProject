<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* , com.dm.common.*"%>




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
    <div>
    	<input type="submit" value="회원 목록 보기" style="margin-left: 40%;" id="memBtn">
    	<input type="submit" value="글 목록 보기" id="queBtn">
    	<div>
<jsp:useBean class="com.dm.common.RegisterDAO" id="regMgr2" scope="session" />    	
<%
ArrayList<RegisterDTO> mlist = (ArrayList<RegisterDTO>)session.getAttribute("vlist");
%>
<table width="75%" align="center" id="memberList">
<tr> 
<td align="center" bgcolor="">
	<table width="95%" align="center" bgcolor="" border="1">
	<tr bgcolor=""> 
	<td align="center"><font color="">회원아이디</font></td>
	<td align="center"><font color="">회원이름</font></td>
	<td align="center"><font color="">패스워드</font></td>
	</tr>
	<%
	for(int i=0; i<mlist.size(); i++){
	RegisterDTO regBean = (RegisterDTO)mlist.get(i);
	%>
	<tr> 
	<td align="center"><%=regBean.getId()%></td>
	<td align="center"><%=regBean.getNickname()%></td>
	<td align="center"><%=regBean.getPassword()%></td>
	<td align="center"><a href="">삭제하기</a></td>
	</tr>
	<%}%>
	</table>
</td>
</tr>
</table>
<%
ArrayList<QuestionDTO> plist = (ArrayList<QuestionDTO>)session.getAttribute("plist");
%>
<table width="75%" align="center" id="questionList">
<tr> 
<td align="center" bgcolor="">
	<table width="95%" align="center" bgcolor="" border="1">
	<tr bgcolor=""> 
	<td align="center"><font color="">글 id</font></td>
	<td align="center"><font color="">글 제목</font></td>
	<td align="center"><font color="">작성자 id</font></td>
	<td align="center"><font color="">카테고리</font></td>
	</tr>
	<%
	for(int i=0; i< plist.size(); i++){
	QuestionDTO regBean2 = (QuestionDTO)plist.get(i);
	%>
	<tr> 
	<td align="center"><%=regBean2.getQuestion_id()%></td>
	<td align="center"><%=regBean2.getQuestion_title()%></td>
	<td align="center"><%=regBean2.getMemeber_id()%>
	<td align="center"><%=regBean2.getcategory()%></td>
	<td align="center"><a href="">삭제하기</a></td>
	</tr>
	<%}%>
	</table>
</td>
</tr>
</table>

<!-- 
	ArrayList<RegisterDTO> vList = regMgr2.selectMemberList();
	for(int i=0; i < vList.size(); i++){
		RegisterDTO regBean = vList.get(i);
		out.println(regBean.getMemberid() + ",");
		out.println(regBean.getPassword() + ",");
		out.println(regBean.getName() + ",");
		out.println(regBean.getEmail() + "<br>");
		System.out.println("jejeje");
	}

 -->
    	
    	</div>
    </div>
        </div>
        
        <div style="margin-bottom: 30px;">
        </div>
    <jsp:include page="module/footer.jsp"></jsp:include>

</body>
<script type="text/javascript">
const ml = document.querySelector("#memberList");
const ql = document.querySelector("#questionList");
const mb = document.querySelector("#memBtn");
const qb = document.querySelector("#queBtn");
ql.style.display = "none";
ml.style.display = "none";
mb.addEventListener("click",mlon)
qb.addEventListener("click",qlon)

function mlon() {
	ml.style.display = "block";
	ql.style.display = "none";
}
	
function qlon() {
	ql.style.display = "block";
	ml.style.display = "none";
}

</script>


</html>

