<%@page import="com.dm.common.StringUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dm.common.CategoryDTO"%>
<%@page import="com.dm.common.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<div class="warp_right">
            <div id="question_btn" class="border">
                <a href="NewQuestion.do">
                    질문하기
                </a>
            </div>

			<div id="mypage_btn" class="border">
			<%
				if(session.getAttribute("isadmin") != null){
					%>
					<a href="list.do">
                    관리자 페이지
                </a>
                <% 
				}
				else{
					%>
					<a href="getinfo.do">
                    마이페이지
                </a>
                <%
				}
			%>
         
            </div>

            <div class="categorys"></div>
    
   	<a href="#" id="today_q_warp">
   		<img src="<%= request.getContextPath() %>/imgs/todayQ.png" id="today_q_img">
   		<span id="today_q_inner_text">오늘의 질문입니다</span>
   	</a>
</div>
            
<script type="text/javascript">
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