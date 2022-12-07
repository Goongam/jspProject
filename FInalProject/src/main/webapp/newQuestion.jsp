<%@page import="java.util.ArrayList"%>
<%@page import="com.dm.common.CategoryDAO"%>
<%@page import="com.dm.common.CategoryDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 질문 작성</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<link rel="stylesheet" type="text/css" href="./css/module.css"> 
<link rel="stylesheet" type="text/css" href="./css/newQuestion.css"> 

<%

	//CategoryDAO cateDAO = new CategoryDAO();
	//ArrayList<CategoryDTO> catelist = cateDAO.selectQuestion();
	ArrayList<CategoryDTO> catelist = (ArrayList)session.getAttribute("catelist");
	String loginCkeck = (String)session.getAttribute("loginCkeck");
	if(loginCkeck == null) response.sendRedirect("login.jsp"); //로그아웃시 로그인페이지 이동
	
	String memberid =(String) session.getAttribute("idValue");
%>

</head>
<body>

	<jsp:include page="module/header.jsp"></jsp:include>
	<div class="container">
		<div class="section">
			<div class="content">
			
				<div class="new_question_box">
					<form method="post" action="InsertQuestion.do" id="newQuestionForm">
						<input type="text" id="title" name="titledata">
						<select name="select_category" id="category_select">
						    <option value="">카테고리 선택</option>
						    <%
						    	for(CategoryDTO category : catelist){
						    		out.print("<option value='"+category.getCategory_name()+"'>"+category.getCategory_name()+"</option>");
						    	}
						    %>
						</select>
	  					<textarea id="summernote" name="editordata"></textarea>
	  					<div class="submit_wrap"><input type="submit" id="submitBTN" value="작성"></div>
	  					<input type="hidden" name="memberId" value="<%= memberid %>">
					</form>
				</div>
			</div>
			<jsp:include page="module/rightwarp.jsp"></jsp:include>
			
		</div>
	
	</div>
	
	
	<jsp:include page="module/footer.jsp"></jsp:include>

<div class="img-uploading">
		이미지 업로드 중...
	</div>
</body>

</html>

<script>
$(document).ready(function() {
	//여기 아래 부분
	$('#summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: false,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정
		  callbacks: {	//여기 부분이 이미지를 첨부하는 부분
				onImageUpload : function(files) {
					uploadSummernoteImageFile(files[0],this);
				},
				onPaste: function (e) {
					var clipboardData = e.originalEvent.clipboardData;
					if (clipboardData && clipboardData.items && clipboardData.items.length) {
						var item = clipboardData.items[0];
						if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
							e.preventDefault();
						}
					}
				}
		  }
	});
});

function uploadSummernoteImageFile(file, editor) {
	data = new FormData();
	data.append("file", file);
	$.ajax({
		data : data,
		type : "POST",
		url : "UploadSummernoteImageFile",
		contentType : false,
		processData : false,
		success : async function(data) {
			$('.img-uploading').css('display','block');
			upload(data.url, editor);
			
			console.log(data.url);
        	//항상 업로드된 파일의 url이 있어야 한다.
			//$(editor).summernote('insertImage', data.url);
		}
	});
}

function checkURL(url, resolve) {
    $("<img>")
    .attr('src', url)
    .on('load', () => {resolve()})
    .on('error', () => console.log("연결시도중..."));
}

let checkURLInterval;
let urlTimeOver;
function getURL(url){
    return new Promise((resolve, reject)=>{
        checkURLInterval = setInterval(()=>checkURL(url, resolve), 500);

        urlTimeOver = setTimeout(reject,10_000);
    });
}

async function upload(url, editor){
    //const check =  await getURL();
    getURL(url).then(()=>{
        $(editor).summernote('insertImage', url);
    }).catch(()=>{
        console.log("실패"); 
    }).finally(()=>{
        clearInterval(checkURLInterval);
        clearTimeout(urlTimeOver);
        $('.img-uploading').css('display','none');
    })
    
}


var f = document.getElementById("newQuestionForm");
f.addEventListener("submit" , function(e) {
	if(f.title.value == '' ) {
	    alert("제목을 입력하세요");
	    e.preventDefault();
	    f.title.focus();
	    return;
	  }
	  if(f.select_category.value == '' ) {
	    alert("카테고리를 선택 하세요");
	    e.preventDefault();
	    f.select_category.focus();
	    return;
	  }
	  if(f.editordata.value == '' ) {
	    alert("내용을 입력하세요");
	    e.preventDefault();
	    f.editordata.focus();
	    return;
	  }
  
  
  
});

</script>