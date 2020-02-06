<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>chatFeed</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
        <br>
        <br>
        <div style="text-align: left">
        				<a href="${pageContext.request.contextPath}/sorties/liste"
        					class="button medium hpbottom">liste des sorties</a>
         <br>
         <br>
			<input type="button" value="Nouveau message"
        					onclick="location.href='${pageContext.request.contextPath}/showChatForm'; return false;"
        					class="btn btn-primary" />
        <br>
        <br>
			<h2>Fil de messagerie chat</h2>
			<br>

			<table class="table table-striped table-bordered">
				<tr>
				    <th>date</th>
					<th>utilisateur</th>
					<th>message</th>



				</tr>

				<c:forEach var="tempFeed" items="${chatFeed}">


					<tr>
						<td><fmt:formatDate type = "both"
                             dateStyle = "short" timeStyle = "short" value="${tempFeed.date}"/></td>
						<td>${tempFeed.utilisateur.identifiant}</td>
						<td>${tempFeed.message}</td>

					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</body>
</html>