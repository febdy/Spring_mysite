<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/user.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
	<title>Join Page</title>
</head>
<body>

	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
			
		<div id="wrapper">
			<div id="content">
				<div id="user">
	
					<form id="join-form" name="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
						
						<label class="block-label" for="name">이름</label>
						<input id="name" name="name" type="text" value="">
	
						<label class="block-label" for="email">이메일</label>
						<input id="email" name="email" type="text" value="">
						<input type="button" id="btn" value="id 중복체크">
						<div id="checkMsg"></div>
						
						<label class="block-label">패스워드</label>
						<input name="password" type="password" value="">
						
						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
							<label>남</label> <input type="radio" name="gender" value="male">
						</fieldset>
						
						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>
						
						<input type="submit" value="가입하기">
						
					</form>
					
				</div><!-- /user -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
				
	</div> <!-- /container -->

</body>

<script type="text/javascript">
	$("#btn").on("click", function(){
		var email = $("#email").val();
		
		$.ajax({		
			url : "${pageContext.request.contextPath}/user/api/emailcheck",		
			type : "post",
		//	contentType : "application/json",
			data : {email: email}, //jsp->servlet 줄 때
			dataType : "json", // servlet->jsp 받을 때

			success : function(result){
				if(result == true)
					$("#checkMsg").text("사용 가능합니다.");
				else
					$("#checkMsg").html("<font color=\"red\">사용중인 이메일입니다.</font>");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
</script>

</html>
