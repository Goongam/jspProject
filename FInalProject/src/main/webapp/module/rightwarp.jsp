<%@page import="java.util.ArrayList"%>
<%@page import="com.dm.common.CategoryDTO"%>
<%@page import="com.dm.common.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	CategoryDAO cateDAO = new CategoryDAO();
	ArrayList<CategoryDTO> catelist = cateDAO.selectQuestion();

%>

<div class="warp_right">
                <div id="question_btn" class="border">
                    <a href="<%=request.getContextPath()%>/newQuestion.jsp">
                        질문하기
                    </a>
                </div>

				<div id="mypage_btn" class="border">
                    <a href="#mypage">
                        마이페이지
                    </a>
                </div>

                <div class="categorys">
                    <%
                    
	                    for(CategoryDTO a : catelist){
	                		out.println("<div><a href='"+a.getCategory_name()+"'>"+a.getCategory_name()+"</a></div>");
	                	}
                    %>
                    
                </div>
                
               	<a href="#" id="today_q_warp">
               		<img src="<%= request.getContextPath() %>/imgs/todayQ.png" id="today_q_img">
               		<span id="today_q_inner_text">오늘의 질문입니다</span>
               	</a>
            </div>