<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
		<div style="text-align: right">
                                    				<a href="${pageContext.request.contextPath}/account/logout"
                                    					class="button medium hpbottom">Se déconnecter</a>
                                    			</div>
                                    			<p>Bienvenue ${sessionScope.user}</p>


			<br>


			<br>

			<input type="button" value="Nouvelle sortie"
        					onclick="window.location.href='showFormSortie'; return false;"
        					class="btn btn-primary" />
         <br>
         <br>
         <br>
         <div style="text-align: left">
                                             				<a href="${pageContext.request.contextPath}/sorties/userSorties"
                                             					class="button medium hpbottom">Sorties auxquelles je participe</a>
                                             			</div>
                         <br>

                 		<br>

        <div style="text-align: left">
                                             				<a href="${pageContext.request.contextPath}/sorties/organisateurSorties"
                                             					class="button medium hpbottom">Sorties que j'organise</a>
                                             			</div>
    <br>
	<h3>Recherche par critère</h3>
			<br>

			<form:form
				action="searchByCriteria"
				cssClass="form-horizontal" method="get">
				<br>
				<div class="form-group">
					<div class="col-md-offset-2 col-md-6">
						<label for="horspiste"></label>
						<select class="form-control"
							name="horspiste">
							<option value="" selected disabled>Hors piste?</option>
							<option value="TRUE">VRAI</option>
							<option value="FALSE">FAUX</option>
						</select>

						<label for="statut"></label>
						<select class="form-control"
							name="statut">
							<option value="" selected disabled>statut</option>
							<option value="Confirme">Confirmé</option>
							<option value="en attente">En attente</option>
							<option value="Termine">Terminé</option>
							<option value="Annule">Annulé</option>
                        </select>

                        <label for="niveau"></label>
                        <select class="form-control"
							name="niveau">
							<option value="" selected disabled>niveau</option>
							<option value="debutant">débutant</option>
							<option value="confirme">Confirmé</option>
							<option value="expert">Expert</option>
                        </select>
                        <br>
						<input name="duree" class="form-control" placeholder="nombre max de jours" />
						<br>
						<button type="submit" class="btn btn-default btn-sm">Chercher</button>
					</div>
				</div>


			</form:form>
            <br>
			<h2>Liste des sorties</h2>

			<table class="table table-striped table-bordered">
				<tr>
				    <th>organisateur</th>
				    <th>date</th>
					<th>nom</th>
					<th>description</th>
					<th>durée</th>
					<th>statut</th>


				</tr>

				<c:forEach var="tempSorties" items="${empty sortiesFound ? sortiesList : sortiesFound}">

					<c:url var="detailsLink"
						value="/sorties/${tempSorties.sortieId}/details">
					</c:url>



					<tr>
						<td>${tempSorties.organisateur.identifiant}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${tempSorties.date}"/></td>
						<td>${tempSorties.nom}</td>
						<td>${tempSorties.description}</td>
						<td>${tempSorties.duree} jours</td>
						<td>${tempSorties.statut}</td>




						<td><a href="${detailsLink}">détails</a></td>



					</tr>
				</c:forEach>
			</table>

		</div>
	</div>

</body>
</html>
