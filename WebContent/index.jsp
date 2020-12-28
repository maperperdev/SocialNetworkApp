<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
	<c:if test="${not empty sessionScope.loggedUser}">
			<c:set var="loggedUser" value="${sessionScope.loggedUser}" scope="page" />
	</c:if>
		<div>

			<c:choose>
				<c:when test="${empty loggedUser}">
					<a href="views/login.jsp">Login new user</a>
					<a href="views/insert.jsp">Create a new user</a>
					<p>
					<p><b>Anonymous</b> user</p>
					<h2>Welcome to the principal page</h2>
				</c:when>
				<c:otherwise>

					<h1>
						<b>Welcome</b>
						<c:out value="${loggedUser.nick}" />
					</h1>

				</c:otherwise>
			</c:choose>

		</div>

	</div>
</body>
</html>