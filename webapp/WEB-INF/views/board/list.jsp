<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>mysite</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="content">
			<div id="board">
				<form id="search_form" action="board" method="get">
					<input type="hidden" name="a" value="search">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:forEach items="${bList}" var="bVo" varStatus="status">
						<tr>
							<td>${bVo.no}</td>
							<td><a href="${pageContext.request.contextPath}/board/view?no=${bVo.no}">${bVo.title}</a></td>
							<td>${bVo.name}</td>
							<td>${bVo.hit}</td>
							<td>${bVo.date}</td>
							<td><c:if test="${bVo.userNo == authUser.no}">
								<a href="${pageContext.request.contextPath}/board/delete?no=${bVo.no}&userNo=${bVo.userNo}" class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>					
				</table>
				
				<div class="pager">
					<c:if test="${where == 'list'}">				
						<ul>
							<c:if test="${page > 1}">
								<li><a href="board?a=list&page=1">◀</a></li>
							</c:if>
							<c:if test="${page <= 1}">
								<li>◀</li>
							</c:if>
						
							<c:forEach var="i" begin="1" end="${maxPageNum}" step="1">
								<c:if test="${page == i}}}">
									<li class="selected"><a href="board?a=list&page=${i}">${numOfPageBundle} + i</a></li>
								</c:if>
								<c:if test="${page != i}}">
									<li><a href="board?a=list&page=${i}">${numOfPageBundle + i}</a></li>
								</c:if>
							</c:forEach>
	
							<c:if test="${page < maxPageNum}">
								<li><a href="board?a=list&page=${maxPageNum}">▶</a></li>
							</c:if>
							<c:if test="${page >= maxPageNum}">
								<li>▶</li>
							</c:if>
						</ul>
					</c:if>
					
					<c:if test="${where == 'search'}">				
						<ul>
							<c:if test="${page > 1}">
								<li><a href="board?a=search&kwd=${kwd}&page=1">◀</a></li>
							</c:if>
							<c:if test="${page <= 1}">
								<li>◀</li>
							</c:if>
							
							<c:forEach var="i" begin="1" end="${maxPageNum}" step="1">
								<c:if test="${page == i}">
									<li class="selected"><a href="board?a=search&kwd=${kwd}&page=${i}">${i}</a></li>
								</c:if>
								<c:if test="${page != i}">
									<li><a href="board?a=search&kwd=${kwd}&page=${i}">${i}</a></li>
								</c:if>
							</c:forEach>
	
							<c:if test="${page < maxPageNum}">
								<li><a href="board?a=search&kwd=${kwd}&page=${maxPageNum}">▶</a></li>
							</c:if>
							<c:if test="${page >= maxPageNum}">
								<li>▶</li>
							</c:if>
						</ul>
					</c:if>				
				</div> <!-- pager -->

				<div class="bottom">
					<c:if test="${not empty authUser}">
						<a href="${pageContext.request.contextPath}/board/writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>

			</div>
		</div>
		
			<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>