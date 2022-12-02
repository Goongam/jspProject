<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                    <div><a href="#category1">#카테고리1</a></div>
                    <div><a href="#category1">#카테고리2</a></div>
                    <div><a href="#category1">#카테고리3</a></div>
                    <div><a href="#category1">#카테고리4</a></div>
                    <div><a href="#category1">#카테고리5</a></div>
                    <div><a href="#category1">#카테고리6</a></div>
                </div>
                
               	<a href="#" id="today_q_warp">
               		<img src="<%= request.getContextPath() %>/imgs/todayQ.png" id="today_q_img">
               		<span id="today_q_inner_text">오늘의 질문입니다</span>
               	</a>
            </div>