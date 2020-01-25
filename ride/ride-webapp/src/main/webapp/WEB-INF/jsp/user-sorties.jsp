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
			<h2>Je participe aux sorties:</h2>
			<br>

			<table class="table table-striped table-bordered">
				<tr>
				    <th>organisateur</th>
				    <th>date</th>
					<th>nom</th>
					<th>description</th>
					<th>durée</th>
					<th>statut organisateur</th>
                    <th>statut participant</th>



				</tr>

				<c:forEach var="tempParticipant" items="${userParticipant}">

					<c:url var="detailsLink"
						value="/sorties/${tempParticipant.sortie.sortieId}/details">
					</c:url>
                    <c:url var="deleteLink" value="/sorties/deleteParticipant">
						<c:param name="participantId" value="${tempParticipant.id}" />
					</c:url>
                    <c:url var="confirmLink" value="/sorties/confirmParticipant">
						<c:param name="participantId" value="${tempParticipant.id}" />
					</c:url>


					<tr>
						<td>${tempParticipant.sortie.organisateur.identifiant}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${tempParticipant.sortie.date}"/></td>
						<td>${tempParticipant.sortie.nom}</td>
						<td>${tempParticipant.sortie.description}</td>
						<td>${tempParticipant.sortie.duree}</td>
						<td>${tempParticipant.sortie.statut}</td>
						<td>${tempParticipant.statut}</td>





						<td><a href="${detailsLink}">détails</a>|
                        <a
								href="${deleteLink}"
								onclick="if (!(confirm('Se désister de la sortie?'))) return false">Me désister</a> |
                       <a
								href="${confirmLink}"
								onclick="if (!(confirm('Confirmer la sortie?'))) return false">Confirmer</a>
						</td>						</td>



					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</body>
</html>