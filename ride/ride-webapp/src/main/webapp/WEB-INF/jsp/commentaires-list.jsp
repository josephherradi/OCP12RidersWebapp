<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>commentaires list</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
        <br>
        <br>
        <div style="text-align: left">
        				<a href="${pageContext.request.contextPath}/sorties/${commentaireList[0].participant.sortie.sortieId}/details"
        					class="button medium hpbottom">détails de sortie</a>
         <br>
         <br>
			<h2>Liste des commentaires</h2>
			<br>

			<table class="table table-striped table-bordered">
				<tr>
				    <th>date</th>
					<th>participant</th>
					<th>message</th>



				</tr>

				<c:forEach var="tempCommentaire" items="${commentaireList}">


					<tr>
						<td>${tempCommentaire.date}</td>
						<td>${tempCommentaire.participant.utilisateur.identifiant}</td>
						<td>${tempCommentaire.message}</td>

					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</body>
</html>