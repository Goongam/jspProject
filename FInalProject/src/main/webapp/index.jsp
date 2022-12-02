<%@page import="com.dm.view.user.Paging"%>
<%@page import="com.dm.common.TimeDiff"%>
<%@page import="com.dm.common.QuestionDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<link rel="stylesheet" type="text/css" href="css/index1.css"> 
<link rel="stylesheet" type="text/css" href="css/module.css"> 

<%
	Paging page_data = (Paging) session.getAttribute("page_data");
	ArrayList<QuestionDTO> qlist = (ArrayList<QuestionDTO>) session.getAttribute("qlist");
	TimeDiff timediff = new TimeDiff();

	//
	int current_page = page_data.getCurrent_page();
	int start_page = page_data.getStart_page();
	int last_page = page_data.getLast_page();
	int list_num = page_data.getList_num();
	int total_page = page_data.getTotal_page();
%>

<body>
    
	<jsp:include page="module/header.jsp"></jsp:include>

    <div class="container">
        <div class="section">
            <div class="content">
            	<div class="qlist">
                <div class="qlist_top">Q&A</div>
                
                <%
	                for(QuestionDTO q : qlist){
	            %>
	            	<div class="question_item">
	                    <h3 class="question_item_title"><a href="Question.do?qustionid=<%= q.getQuestion_id()%>"><%= q.getQuestion_title().replaceAll("<[^>]*>", " ") %></a></h3>
	                    <div class="question_item_content"><%=q.getQuestion_contnet().replaceAll("<[^>]*>", " ") %></div>
	                    <div class="question_item_sub">
	                        <div class="question_item_sub_category">#JAVA</div>
	                        <ul class="question_item_sub_ul">
	                            <li><%= timediff.getTimeDiff(q.getEdit_time()) %></li>
	                            <li>답변 1</li>
	                            <li><%= q.getViews() %></li>            
	                        </ul>
	                    </div>
                	</div>
	            
	            
	            <%
	            	}
                
                %>
                
                <div class="paging_box">
                    <div class="paging_warp">
                        <%
                        	
                        	if(start_page != 1) {
                        		%>
                    			<a 
                    				href=<%=request.getContextPath()+"/index.do?"+"p="+(start_page-1)+"&list_num="+list_num%>
                    				class="icon prev"
                    			>이전</a>
                    			<%
                        	}
                        	
                        
                        	for(int i = start_page; i <= last_page ; i++){
                        		
                        		String isCurrent = (i == current_page) ? "current_page" : "";
      
                        		%>
                        			<a 
                        				href=<%=request.getContextPath()+"/index.do?"+"p="+i+"&list_num="+list_num%>
                        				class="<%= isCurrent %>"
                        			><%=i %></a>
                        		<%
                        	}
                        	
                        	if(last_page != total_page) {
                        		%>
                    			<a 
                    				href=<%=request.getContextPath()+"/index.do?"+"p="+(last_page+1)+"&list_num="+list_num%>
                    				class="icon next"
                    			>다음</a>
                    			<%
                        	}
                        %>

                    </div>
                    
                </div>
            </div>
            </div>
            <jsp:include page="module/rightwarp.jsp"></jsp:include>
        </div>
        
    </div>
    <jsp:include page="module/footer.jsp"></jsp:include>

</body>
</html>

