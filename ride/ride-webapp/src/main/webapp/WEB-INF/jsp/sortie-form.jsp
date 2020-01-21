<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sortie</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-10">

			<div class="panel-body">

				<br>
				<button type="button" name="back" onclick="history.back()">back</button>

				<br>
		 <h2>Sortie</h2>

            <div class="form-group">
			<div class="col-md-offset-2 col-md-9">
				<form:form action="/sorties/saveFormSortie" cssClass="form-horizontal" method="post"  enctype="multipart/form-data" modelAttribute="laSortie">

					<form:hidden path="sortieId" />
					<form:hidden path="organisateur"/>

					<div class="form-group">
						<label for="nom" class="col-md-3 control-label">nom</label>
						<div class="col-md-9">
							<form:input path="nom" cssClass="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="description" class="col-md-3 control-label">description</label>
						<div class="col-md-9">
							<form:input path="description" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-md-3 control-label">date (YYYY/MM/DD)</label>
						<div class="col-md-9">
							<form:input path="date" cssClass="form-control" />
						</div>
					</div>


					<div class="form-group">
						<label for="nbrParticipants" class="col-md-3 control-label">nombre participants</label>
						<div class="col-md-9">
							<form:input path="nbrParticipants" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="duree" class="col-md-3 control-label">durée</label>
						<div class="col-md-9">
							<form:input path="duree" cssClass="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label for="distance" class="col-md-3 control-label">distance (km)</label>
						<div class="col-md-9">
							<form:input path="distance" cssClass="form-control" />
						</div>
					</div>
                    <div class="form-group">
						<label for="nbrEtapes" class="col-md-3 control-label">nombre étapes</label>
						<div class="col-md-9">
							<form:input path="nbrEtapes" cssClass="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="niveau" class="col-md-3 control-label">niveau</label>
						<div class="col-md-9">
							<form:select path="niveau" cssClass="form-control">
								<form:option value="debutant">débutant</form:option>
								<form:option value="confirme">confirmé</form:option>
								<form:option value="expert">expert</form:option>
							</form:select>
						</div>
					</div>

					<div class="form-group">
                    						<label for="horspiste" class="col-md-3 control-label">horspiste</label>
                    						<div class="col-md-9">
                    							<form:select path="horspiste" cssClass="form-control">
                    								<form:option value="True">Vrai</form:option>
                    								<form:option value="False">Faux</form:option>
                    							</form:select>
                    						</div>
                    					</div>
                    <div class="form-group">
                   						<label for="file" class="col-md-3 control-label">Fichier KML</label>
                   						<div class="col-md-9">
                   							<input type="file" name="file" />
                   						</div>
                   					</div>

                    <div class="form-group">
						<div class="col-md-offset-2 col-md-9">
							<button id="submit" cssClass="btn btn-primary">Submit</button>
						</div>
					</div>

				   </form:form>
                    </div>
                  </div>






			</div>
		</div>
	</div>
</body>
</html>