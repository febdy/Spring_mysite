<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
	
	<title>Guestbook</title>
</head>
<body>

	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<table>
						<tr>
							<td>이름</td>
							<td><c:choose>
									<c:when test="${empty authUser}">
										<input type="text" name="name" />
									</c:when>
									<c:otherwise>
										${authUser.name}
										<input type="hidden" name="name" value="${authUser.name}">
									</c:otherwise>
								</c:choose></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td colspan=4><textarea class="content" name="content"
									id="content" placeholder="내용을 입력해주세요."></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="button" id="btnAdd" VALUE=" 확인 " /></td>
						</tr>
					</table>

					<ul id="listArea"></ul>
					
					<input type="button" id="btnNext" value="다음글 5개 가져오기">
					
				</div><!-- /guestbook -->
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div> <!-- /container -->


	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input type="password" name="modalPassword" id="modalPassword"><br>	
					<input type="text" name="modalPassword" value="" id="modalNo"> <br>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del" data-dismiss="modal">삭제</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->


</body>

<script type="text/javascript">

	var page = 1;
	
	$(document).ready(function(){
		fetchList(page);
	});
	
	$("#btnAdd").on("click", function(){ // add
		var boardVo = {
			'name' : $("input[name=name]").val(),
			'password' : $("input[name=password]").val(),
			'content' : $(".content.content").val()
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/guestbook/api/add",
			type : "post",
			data : JSON.stringify(boardVo),
			contentType : "application/json; charset=UTF-8",
			dataType : "json",
			success : function(result){
				render(result, "up");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	
		$("[name=name]").val("");
		$("[name=password]").val("");
		$(".content.content").val("");
	});
	
	$("ul").on("click", ".delete", function(){ // delete
		$("#del-pop").modal();
		$("#modalNo").val(this.id);		
	});
	
	$("#btn_del").on("click", function(){
		var modalNoVal = $("#modalNo").val();
		var modalPasswordVal = $("#modalPassword").val();
			
		$.ajax({
			url : "${pageContext.request.contextPath}/guestbook/api/delete",
			type : "post",
			data : {no : modalNoVal, password : modalPasswordVal},
			dataType : "json",
			success : function(result){
				if(result == 1)
					$("#g" + modalNoVal).remove();
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		$("#modalPassword").val("");
	});
	
	$("#btnNext").on("click", function(){ // load next page
		page += 1;
		
		fetchList(page);
	});
	
	
	function fetchList(page){
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
		str += "<li id='g"+ guestVo.no +"'>";
		str += "	<table>";
		str += "		<tr>";
		str += "			<td>"+ guestVo.no +"</td>";
		str += "			<td>"+ guestVo.name +"</td>";
		str += "			<td>"+ guestVo.date +"</td>";
		str += "			<td><a class='delete' id='"+ guestVo.no +"'>삭제</a></td>";
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