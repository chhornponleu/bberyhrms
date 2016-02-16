<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="header">
		<div class="head">
			<div class="logo">
				<%-- <img src="<c:url value='/resources/images/logo.png' />" /> --%>
			</div>
			<div class="info">
				<h3>Please Login</h3>
				<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
					<p>${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}</p>
				</c:if>
			</div>
		</div>
	</div>
	<div class="login">
		<form method="post" action="<c:url value='/auth/attemp' />">
			<label for="id">Name</label> 
			<br>
			<input type="text" class="text" placeholder="Please Enter Username" name="username" /> 
			
			<br>
			<br>
			<label for="password">Password</label>
			<br> 
			<input type="password" class="text" placeholder="Please Enter Password" name="password" />
			
			<br>
			<br>
			<button type="submit" class="btn">Login</button>
		</form>
	</div>
</body>
</html>