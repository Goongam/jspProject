
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    String logincheck = (String)session.getAttribute("idValue");
    %>
<div class="top_gnb">
    <div class="top">
        <div id="logo">
        	<a href="<%= request.getContextPath() %>/index.do"><img src="<%= request.getContextPath() %>/imgs/banner.png" width="140px" style="margin-left: 10px; margin-top: 4px;"></a>
        </div>
        <input type="text" id="search_textArea" class="top_cell" placeholder=" 검색"></input>
        <a href="#search" id="search_btn" class="top_cell">검색</a>
        
       <%
       	
        if(logincheck != null) {
        	%>
        	<a href="logout.do" id="login" class="top_cell">로그아웃</a>
        	 <%
        }
        else{
        	%><a href="<%= request.getContextPath()%>/login.jsp" id="login" class="top_cell">로그인</a><%
        }
        %>
        
        
    </div>
</div>