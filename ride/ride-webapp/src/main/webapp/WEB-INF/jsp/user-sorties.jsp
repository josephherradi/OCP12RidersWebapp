<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>sorties list</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">

         <br>
         <br>
			<h2>Mes sorties</h2>

			<table class="table table-striped table-bordered">
				<tr>
				    <th>organisateur</th>
				    <th>date</th>
					<th>nom</th>
					<th>description</th>
					<th>durée</th>
					<th>statut</th>


				</tr>

				<c:forEach var="tempParticipant" items="${userParticipant}">

					<c:url var="detailsLink"
						value="/sorties/${tempParticipant.sortie.sortieId}/details">
					</c:url>



					<tr>
						<td>${tempParticipant.sortie.organisateur.identifiant}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${tempParticipant.sortie.date}"/></td>
						<td>${tempParticipant.sortie.nom}</td>
						<td>${tempParticipant.sortie.description}</td>
						<td>${tempParticipant.sortie.duree}</td>
						<td>${tempParticipant.sortie.statut}</td>




						<td><a href="${detailsLink}">détails</a></td>



					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</body>
</html>