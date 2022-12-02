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
    	id 이름 비밀번호 
    	id 글 제목 작성자 id 카테고리
    	
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

