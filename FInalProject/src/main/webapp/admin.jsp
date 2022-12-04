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
    
        <div class="top_gnb">
            <div class="top">
                <div id="logo" class="top_cell"><img src="./imgs/banner.png" width="140px" style="margin-left: 10px; margin-top: 4px;"></div>
                <input type="text" id="search_textArea" class="top_cell" placeholder=" 검색"></input>
                <a href="#search" id="search_btn" class="top_cell">검색</a>
                <a href="#login" id="login" class="top_cell">로그인</a>
            </div>
        </div>

    <div class="container">
    <div>
    	<input type="submit" value="회원 목록 보기">
    	<input type="submit" value="글 목록 보기">
    	<div>
<jsp:useBean class="com.dm.common.RegisterDAO" id="regMgr2" scope="session" />    	
<%
ArrayList<RegisterDTO> mlist = (ArrayList<RegisterDTO>)session.getAttribute("vlist");
%>
<table width="75%" align="center" bgcolor="#FFFF99">
<tr> 
<td align="center" bgcolor="#FFFFCC">
	<table width="95%" align="center" bgcolor="#FFFF99" border="1">
	<tr bgcolor="#996600"> 
	<td align="center"><font color="#FFFFFF">회원아이디</font></td>
	<td align="center"><font color="#FFFFFF">회원이름</font></td>
	<td align="center"><font color="#FFFFFF">패스워드</font></td>
	</tr>
	<%
	for(int i=0; i<mlist.size(); i++){
	RegisterDTO regBean = (RegisterDTO)mlist.get(i);
	%>
	<tr> 
	<td align="center"><%=regBean.getMemberid()%></td>
	<td align="center"><%=regBean.getName()%></td>
	<td align="center"><%=regBean.getPassword()%></td>
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

    	id 이름 비밀번호 
    	id 글 제목 작성자id 카테고리
    	
    	</div>
    </div>
        </div>
    <footer>
        <div class="footer_img"><img src="./imgs/banner.png"></div>
        <div class="footer_box">
            <span>소개</span>
            <span>연락처</span>
            <span>문의</span>
            <span>개인정보 처리방침</span>
            <span>서비스 이용약관</span>
        </div>
    </footer>

</body>
</html>

