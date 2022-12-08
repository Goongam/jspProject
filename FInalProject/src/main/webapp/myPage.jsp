<%@page import="com.dm.common.AnswerDAO"%>
<%@page import="com.dm.common.StringUtil"%>
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

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/index1.css"> 
<link rel="stylesheet" type="text/css" href="css/module.css"> 



<body>
    
	<jsp:include page="module/header.jsp"></jsp:include>

    <div class="container">
        <div class="section">
            <div class="content">
            	<div class="qlist">
                <div class="qlist_top">누구누구 님의 마이페이지 	 </div>
                	<div style="width: 100%; background-color:lightgray; height: 400px; margin-top: 10px; border-radius: 0.5rem;
        					box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45); overflow: auto; ">
                		<div style="width: 15%; height: 150px; margin-top:40px; margin-left: 40px; background-color:yellow; float: left;" >
                		<img alt="asdf" src="imgs/banner.png"></div>
                		<div style="background-color: green; width: 60%; height: 150px; margin-top:40px; margin-left: 40px; float: left;">자기소개</div>
                		<button style="float: right; margin-top:10px; margin-right: 10px; font-size: 15px; background-color: lightblue; color: white; ">정보수정</button>
                		<div style="background-color: lightgreen; width: 15%; height: 150px; margin-top:40px; margin-left: 40px; float: left;">뱃지</div>
                		<div style="background-color: lightblue; width: 60%; height: 150px; margin-top:40px; margin-left: 40px; float: left;">
                		<h3>~~님의 관심 카테고리</h3><br>
              			~~~님의 관심 카테고리는 <button>1</button> <button>2</button> <button>3</button> 입니다.</div>
                	</div>
                	<div style="width: 100%; background-color:lightgray; height: 200px; margin-top: 10px; border-radius: 0.5rem;
        					box-shadow: 0.05rem 0.1rem 0rem -0.03rem rgba(0, 0, 0, 0.45); overflow: auto; ">
                		<div style="background-color: lightblue; width: 40%; height:70px; float: left; margin-top: 20px; margin-left: 20px;" >
                		<h2 style="font-weight: bold;">오늘의 방문횟수 : </h2></div>
                		<div style="background-color: lightblue; width: 40%; height:70px; float: left; margin-top: 20px; margin-left: 20px;" ><h2 style="font-weight: bold;">오늘의 방문횟수 : </h2></div>
                		<div style="background-color: lightblue; width: 40%; height:70px; float: left; margin-top: 20px; margin-left: 20px;" ><h2 style="font-weight: bold;">오늘의 방문횟수 : </h2></div>
                		<div style="background-color: lightblue; width: 40%; height:70px; float: left; margin-top: 20px; margin-left: 20px;" ><h2 style="font-weight: bold;">오늘의 방문횟수 : </h2></div>
                <div class="paging_box">
                    <div class="paging_warp">
                      

                    </div>
                    
                </div>
            </div>
            </div>
            
        </div>
        <div style="width: 20%; height: 300px; background-color: lightblue; margin: 0 auto; margin-top: 10px;" ></div>
        <div style="width: 20%; height: 300px; background-color: lightgray; margin-top: 10px; float: right;"></div>
    </div>
    <jsp:include page="module/footer.jsp"></jsp:include>

</body>
</html>

