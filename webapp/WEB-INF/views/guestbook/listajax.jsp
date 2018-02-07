<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

	<title>Guestbook</title>
</head>
<body>

	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">
					
					<form action="${pageContext.request.contextPath}/guestbook/add" method="post">		
						<table>
							<tr>
								<td>이름</td><td>
								<c:choose>
									<c:when test="${empty authUser}">
										<input type="text" name="name" />
									</c:when>
									<c:otherwise>
										${authUser.name}
										<input type="hidden" name="name" value="${authUser.name}">
									</c:otherwise>
								</c:choose>
								</td>
								<td>비밀번호</td><td><input type="password" name="password" /></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="content" id="content" placeholder="내용을 입력해주세요."></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE=" 확인 " /></td>
							</tr>
						</table>
					</form>
					
					<ul id="listArea">
					
					</ul>
					
					<input type="button" id="btnNext" value="다음글 5개 가져오기">
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div> <!-- /container -->

</body>

<script type="text/javascript">

	var page = 1;
	
	$(document).ready(function(){
		console.log(page);
		ajaxGetList(page);
	});
	
	$("#btnNext").on("click", function(){
		page += 1;
		
		ajaxGetList(page);		
	});
	
	function ajaxGetList(page){
		$.ajax({
			url : "${pageContext.request.contextPath}/guestbook/api/list",
			type : "post",
			data : {page : page},
			dataType : "json",
			success : function(gList){			
				for(var i = 0; i < gList.length; i++) {
					render(gList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	}
	
	function render(guestVo, updown){
		var str="";
		str += "<li>";
		str += "	<table>";
		str += "		<tr>";
		str += "			<td>"+ guestVo.no +"</td>";
		str += "			<td>"+ guestVo.name +"</td>";
		str += "			<td>"+ guestVo.date +"</td>";
		str += "			<td><a href='${pageContext.request.contextPath}/guestbook/deleteform?no=${gVo.no}'>삭제</a></td>";
		str += "		</tr>";
		str += "		<tr>";
		str += "			<td colspan=4>"+ guestVo.content+"</td>";
		str += "		</tr>";
		str += "	</table>";
		str += "	<br>";
		str += "</li>";
		
		if(updown == "up")
			$("#listArea").prepend(str);
		else if(updown == "down")
			$("#listArea").append(str);
		else
			console.log("updown 오류");
	}
	
</script>
</html>