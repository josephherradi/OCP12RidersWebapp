<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>étapes list</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
        <br>
        <br>
        				<button type="button" name="back" onclick="history.back()">back</button>

        <br>
         <br>
			<h2>Liste des étapes</h2>
			<br>

			<table class="table table-striped table-bordered">
				<tr>
				    <th>nom</th>
					<th>km à vol d'oiseau</th>
					<th>commentaire</th>



				</tr>

				<c:forEach var="tempEtape" items="${listetapes}">
                    <c:url var="commentLink"
						value="/etapes/getEtape?etapeId=${tempEtape.etapeId}">
					</c:url>

					<tr>
						<td>${tempEtape.nom}</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${tempEtape.distance}" /></td>
						<td>${tempEtape.description}</td>

						<td><a href="${commentLink}">Commenter</a></td>

					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</body>
</html>