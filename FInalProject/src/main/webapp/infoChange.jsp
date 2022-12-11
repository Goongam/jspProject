<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
    <head>
        <title>SIGNUP</title>
        <link rel="stylesheet" href="css/login2.css">
    </head>
    <body>
    <div style="width:400px; margin:auto; border-radius: 0.5rem;
        box-shadow: 0.2rem 0.30rem 0.2rem -0.12rem rgba(0, 0, 0, 0.45);">
			<div style="text-align : center;">
			<a href="index.do">
				<img src="imgs/banner.png"><br>
				</a>
				<h2 style="font-size: 18px; color: #6A24FE;">회원 정보 변경</h2>
            </div>
       <form action="change.do" method="post" id="changeForm">
        <div>
            <b class="input_label">닉네임 변경</b><br>
            <input class="text" type="text" name="changeNickname" placeholder="닉네임" value="<% out.println(session.getAttribute("nickName")); %>"><br><br>
        </div>
        <div>
            <b class="input_label">비밀번호 변경</b><br>
            <input class="text" type="password" name="changePw" id="user_pass" placeholder="패스워드"><br><br>
        </div>
        <div>
            <b class="input_label">비밀번호 확인</b><br>
            <input class="text" type="password" name="changePwCheck" id="chpass" placeholder="패스워드확인"><br>
            <font name="check" size="2" color="red"></font>
						<br><br>
        </div>
				<div>
					<b class="input_label">프로필 이미지 변경</b> <br>
					<input class="text" type="text" name="changeProfileImg" placeholder="이미지 주소" id="img_url_input" value="<%=session.getAttribute("profileImg") %>" style="width: 230px;" hidden><input type="file" value="찾아보기" style="margin-left: 5px;" id="profile_img_input"><br><br>
        </div>
        <div>
					<h3> 자기소개 변경(200자 이내)</h3>
					<textarea rows="5" cols="40" name="changeIntroduce"><% if(session.getAttribute("introduce") != null){
							out.println(session.getAttribute("introduce")); 
							}
							else{
								out.println("");
							}
							%></textarea>
				
        </div>
						<br>
            <input type="submit" class="sign_up" value="회원정보 변경" style="margin-bottom: 20px; color: white;"></input>
            </form>		
        </div>
    
    </body>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	let isPassSame = false;
	$(function(){
	 $('#user_pass').keyup(function(){
	  $('font[name=check]').text('');
	 }); //#user_pass.keyup
	
	 $('#chpass').keyup(function(){
	  if($('#user_pass').val()!=$('#chpass').val()){
	   $('font[name=check]').text('');
	   $('font[name=check]').html("암호틀림");
	   isPassSame = false;
	  }else{
	   $('font[name=check]').text('');
	   $('font[name=check]').html("암호맞음");
	   isPassSame = true;
	  }
	 }); //#chpass.keyup
	});
	 
 
	
 $('#changeForm').submit(function() { //submit이 발생하면

	 	
		if(this.changeNickname.value == '' ){
	 		this.changeNickname.style.borderColor = "red";
	 		return false;
	 	}
		
		if(this.changePw.value == '' ){
	 		this.changePw.style.borderColor = "red";
	 		return false;
	 	}
		if(this.changePwCheck.value == '' ){
	 		this.changePwCheck.style.borderColor = "red";
	 		return false;
	 	}
		if(!isPassSame){
	 		this.changePwCheck.style.borderColor = "red";
	 		return false;
	 	}

		let form= document.querySelector("#changeForm");
    	form.submit();
		
	    return false; //기본 동작인 submit의 동작을 막아 페이지 reload를 막는다.
	});

var textArr = document.querySelectorAll(".text");
textArr.forEach(ele => {
	 ele.addEventListener("input",(e)=>{
		 e.target.style.borderColor = "";
	 });
});



let img_input = document.querySelector("#profile_img_input");
let url_text_input = document.querySelector("#img_url_input");
img_input.onchange = async ()=>{
	console.log(img_input.files[0]);
	//UploadSummernoteImageFile
	const formData = new FormData();
	formData.append("file", img_input.files[0]);
	
	url = await fetch('UploadSummernoteImageFile',{
        method: 'POST',
        body: formData,
    }).then((data)=>data.json())
    .then((json)=>json.url);
	
	url_text_input.value = url;
	console.log(url);
}
</script>
</html>

