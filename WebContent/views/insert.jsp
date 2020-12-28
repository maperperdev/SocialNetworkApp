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
<form action="${pageContext.request.contextPath}/ServletInsertUser" method="post">
		<table>
			<tr>
				<td><label for="nick">Nick</label></td>
				<td><input type="text" name="nick"
					value="${loggedUser.nick}" /></td>
			</tr>
			<tr>
				<td><label for="email">Email</label></td>
				<td><input type="text" name="email"
					value="${loggedUser.email}" /></td>
			</tr>
			<tr>
				<td><label for="dateBirth">Date of Birth</label></td>
				<td><input type="text" name="dateBirth"
					placeholder="dd/mm/aaaa" value="${loggedUser.dateBirth}" /></td>
			</tr>
				<tr>
					<td><label for="password">Password</label></td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td><label for="repeatedPassword">Repear your password</label></td>
					<td><input type="password" name="repeatedPassword" /></td>
				</tr>

			</table>

<br>
			<textarea name="bio" placeholder="Describe yourself" style="resize:none" rows="10" cols="50">
			</textarea>
<br><br>
		<input type="submit" value="Send">
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