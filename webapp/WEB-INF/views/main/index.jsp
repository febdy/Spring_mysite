<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />

		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img style="width: 150px" id="profile" src="${pageContext.request.contextPath}/assets/images/profile.jpg">
					 << 귀여운 삼색이<br>
					 <h2>안녕하세요.<br> 이다영의 mysite에 방문하신 것을<br/> 환영합니다.</h2>
					<p>
						↑ 귀여운 삼색이
						<br>
						<br>
						<a href="guestbook?a=list">방명록</a>에 글 남기기
						<br>
					</p>
				</div>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>