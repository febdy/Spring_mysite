<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
	<title>GuestBook Delete Content.</title>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="wrapper">
			<div id="content">
				<div id="guestbook" class="delete-form">
					
					<form method="post" action="guestbook">
						<label>비밀번호</label>
						<input type="password" name="password">
						<input type="hidden" name="no" value="${no}">
						<input type="hidden" name="a" value="delete">
						<input type="submit" value="확인">
					</form>
					<a href="">방명록 리스트</a>
					
				</div>
			</div><!-- /content -->
		</div><!-- /wrapper -->
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div> <!-- /container -->

</body>
</html>
