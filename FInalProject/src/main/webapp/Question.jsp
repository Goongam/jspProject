<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.*, com.dm.common.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>


<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/module.css"> 
<link rel="stylesheet" type="text/css" href="css/Question.css"> 
<link rel="stylesheet" type="text/css" href="./css/newQuestion.css"> 

</head>

<%

	QuestionDTO question = (QuestionDTO)session.getAttribute("questionData");
	ArrayList<AnswerDTO> answers = (ArrayList<AnswerDTO>)session.getAttribute("answerData");
	HashMap answerVoteTable =(HashMap) session.getAttribute("answerVoteTable");
	
	
	String memberid = (String)session.getAttribute("idValue");
	String loginCkeck = (String)session.getAttribute("loginCkeck");
	TimeDiff timediff = new TimeDiff();

%>

<body>

	<jsp:include page="module/header.jsp"></jsp:include>
	
	<div class="container">
		<div class="section">
			<div class="content">
				<div class="question">
					<div class="question_inner">
						<div class="question_member_info">
							<img class="profile_img" src="imgs/todayQ.png">
							<span><%= question.getMemeber_id() %></span>
							<span><%= timediff.getTimeDiff(question.getEdit_time().getTime()) %></span>
							<span>👁️ <%= question.getViews() %></span>
                        </div>
						<div><h1 class="question_title"> <%= question.getQuestion_title() %> </h1></div>
						<div class="question_content"> <%= question.getQuestion_contnet() %> </div>
					</div>
				</div>
				<div class="answer_count">답변 <%=answers.size() %>개</div>
				<div class="answer">
					<%
						for(AnswerDTO answer : answers){
							%>
							<div class="answer_inner">
								<div class="answer_info_warp">
									<div class="question_member_info">
										<img class="profile_img" src="imgs/todayQ.png">
										<span><%= answer.getMember_id() %></span>
										<span><%= timediff.getTimeDiff(answer.getEdit_time().getTime()) %></span>
			                        </div>
			                        <div class="answer_vote">
			                        	<button onclick="vote(<%=answer.getId()%>, '<%=memberid%>', 'UP')" class="vote_btn vote_btn_left">&#128077;</button>
			                        	<span class="vote_count vote_<%=answer.getId()%>"><%=answerVoteTable.get(answer.getId()) %></span>
			                        	<button onclick="vote(<%=answer.getId()%>, '<%=memberid%>', 'DOWN')" class="vote_btn vote_btn_right">&#128078;</button>
			                        </div>
								</div>
								<div><h1 class="answer_title"> <%= answer.getTitle() %> </h1></div>
								<div class="answer_content"> <%= answer.getContent() %> </div>
							</div>
							<%
						}
					%>
				</div>
				
				<div class="new_answer_text">답변 작성</div>
				<div class="new_answer_box">
					<form method="post" action="InsertAnswer.do" id="newAnswerForm">
						<input type="text" id="title" name="titledata">
	  					<textarea id="summernote" name="editordata"></textarea>
	  					<input type="submit" id="submitBTN">
	  					<input name="QuestionId" value="<%=question.getQuestion_id() %>" type="hidden"></input>
	  					<input type="hidden" name="memberId" value="<%= memberid %>">
					</form>
				</div>
			</div>
			<jsp:include page="module/rightwarp.jsp"></jsp:include>
			
		</div>
	
	</div>
	
	
	<jsp:include page="module/footer.jsp"></jsp:include>
</body>
<script>
	const loginCheck = "<%=loginCkeck%>";

	async function vote(id, memberid, isUp){
		if(loginCheck === "null") {
			if(confirm("로그인이 필요한 기능입니다. 로그인 페이지로 이동하시겠습니까?"))
				location.href = "login.jsp";
			return;
		}

		const {result, voteCount} = await fetch("AnswerVote.do?AnswerId="+id+"&memberId="+memberid+"&isUp="+isUp).then(async (res)=>await res.json()).catch(e => {console.log("데이터 입력 실패")});
		if(result === "already_vote") alert("이미 입력하였습니다");
		else if(result === "success")
			document.querySelector(".vote_"+id).innerText = voteCount;
		else console.log("서버오류");

	}

	
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


	var f = document.getElementById("newAnswerForm");
	f.addEventListener("submit" , function(e) {
		if(loginCheck === "null"){
			e.preventDefault();
			if(confirm("로그인이 필요한 기능입니다. 로그인 페이지로 이동하시겠습니까?"))
				location.href = "login.jsp";
			return;
		}
		
	  if(f.title.value == '' ) {
	    alert("제목을 입력하세요");
	    e.preventDefault();
	    f.title.focus();
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
</html>