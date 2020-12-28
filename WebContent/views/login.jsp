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
	<h1>Iniciar sesión</h1>

	<form action="${pageContext.request.contextPath}/ServletLoginUser"
		method="post">
		<table>
			<tr>
				<td><label for="email">Email</label></td>
				<td><input type="text" name="email"
					value="${sessionScope.loggedUser.email}" /></td>
			</tr>

			<tr>
				<td><label for="password">Password</label></td>
				<td><input type="password" name="password" /></td>
			</tr>

		</table>

		<input type="submit" value="Login">

	</form>
	
	
	<c:set var="errorsList" value="${sessionScope.errorsList}"
		scope="page" />
	<c:choose>
		<c:when test="${not empty errorsList}">
			<div>
				<c:forEach var="error" items="${errorsList}">
					<p>
						<c:out value="${error}" />
					</p>
				</c:forEach>
			</div>
		</c:when>
	</c:choose>
</body>
</html>