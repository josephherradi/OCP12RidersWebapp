<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>details sortie</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
      #map-canvas {
       height: 500px;
       width: 500px;
       overflow: hidden;
       float: left;
       border: thin solid #333;
       }
       .gm-style-iw {
             text-align: center;}
      #capture {
           height: 500px;
           width: 200px;
           overflow: hidden;
           float: right;
           }

    </style>
</head>

<body>
	<div class="container">
		<div class="col-md-offset-2 col-md-8">
		<br>
		<br>
				<div style="text-align: right">Statut
                </div>
				<div style="text-align: right">
        				<a href="${pageContext.request.contextPath}/sorties/confirmSortie?sortieId=${laSortie.sortieId}"
        					class="button medium hpbottom" onclick="if (!(confirm('Confirmer la sortie?'))) return false">Confirmé</a>
        			</div>
				<div style="text-align: right">
        				<a href="${pageContext.request.contextPath}/sorties/annuleSortie?sortieId=${laSortie.sortieId}"
        					class="button medium hpbottom" onclick="if (!(confirm('Annuler la sortie?'))) return false">Annulé</a>
        			</div>
				<div style="text-align: right">
        				<a href="${pageContext.request.contextPath}/sorties/termineSortie?sortieId=${laSortie.sortieId}"
        					class="button medium hpbottom" onclick="if (!(confirm('Terminer la sortie?'))) return false">Terminé</a>
        			</div>

			<br>


			<br>


            			<input type="button" value="Participer à la sortie"
                    					onclick="window.location.href='joinSortie'; return false;"
                    					class="btn btn-primary" />
             <br>
             <br>
<div style="text-align: left">
        				<a href="${pageContext.request.contextPath}/etapes/etapesList?sortieId=${laSortie.sortieId}"
        					class="button medium hpbottom">Liste des étapes</a>
        			</div>
			<h2>Détail de la sortie </h2>
			<br>
			<table class="table table-striped table-bordered">
				<tr>
				    <th>organisateur</th>
					<td>${laSortie.organisateur.identifiant}</td>
				</tr>
				<tr>
				    <th>date</th>
					<td>${laSortie.date}</td>
				</tr>
				<tr>
    				<th>nom</th>
					<td>${laSortie.nom}</td>
				</tr>
				<tr>
					<th>description</th>
					<td>${laSortie.description}</td>
				</tr>

				<tr>
					<th>durée</th>
					<td>${laSortie.duree}</td>
				</tr>
                <tr>
					<th>Nombre étapes</th>
					<td>${laSortie.nbrEtapes}</td>
				</tr>

                <tr>
					<th>nombre de participants</th>
					<td>${laSortie.nbrParticipants}</td>
				</tr>
                <tr>
					<th>niveau</th>
					<td>${laSortie.niveau}</td>
				</tr>
                <tr>
					<th>durée (jours)</th>
					<td>${laSortie.duree}</td>
				</tr>
                <tr>
					<th>distance (km)</th>
					<td>${laSortie.distance}</td>
				</tr>
                <tr>
					<th>Hors piste</th>
					<td>${laSortie.horspiste}</td>
				</tr>
                <tr>
                   <th>Statut</th>
                   <td>${laSortie.statut}</td>
                </tr>


			</table>

		<div id="map-canvas"></div>
		<div id="capture"></div>
		<br>
		</div>
				<div class="col-md-offset-2 col-md-8">


                	<div style="text-align: left">
                                				<a href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/download/${laSortie.filename}"
                               					class="button medium hpbottom">Télécharger itinéraire KML</a>

         <br>
         <br>
                   <table class="table table-striped table-bordered">
                    <tr>
                                    <th>Participants</th>
                                    <th>statut</th>

                    </tr>

                  <c:forEach var="tempParticipants"
                  					items="${laSortie.participants}">
                                 <tr>
                 					<td>${tempParticipants.utilisateur.identifiant}</td>
                 					<td>${tempParticipants.statut}</td>

                 				</tr>
                  </c:forEach>
                 </table>
                 <br>
              	</div>
              	</div>




	</div>

<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=">

</script>
<script src="/js/geoxml3.js"></script>
<script>
      var map;
      var src = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/kml/${laSortie.filename}';
      var geoJSON;
  var request;
  var gettingData = false;
  var openWeatherMapKey = "38bd83905c95523889c2d9535019a9bd"

      function initMap() {
        map = new google.maps.Map(document.getElementById('map-canvas'), {
          mapTypeId: 'roadmap',
          zoom: 4
        });
        google.maps.event.addListener(map, 'idle', checkIfDataRequested);

                      // Sets up and populates the info window with details
                      map.data.addListener('click', function(event) {
                        infowindow.setContent(
                         " <img src=" + event.feature.getProperty("icon") + ">"
                         + "<br /><strong> " + event.feature.getProperty("city") + "</strong>"
                         + "<br /> " + event.feature.getProperty("temperature") + "&deg;C"
                         + "<br /> " + event.feature.getProperty("description")
                         + "<br /> " + "humidité: "+ event.feature.getProperty("humidity")+" %"
                         + "<br /> " + "vitesse vent : "+ event.feature.getProperty("windSpeed")*3.6+" km/h"
                         + "<br /> " + "Pression : "+ event.feature.getProperty("pressure")+" hPa"
                         + "<br /> " + "Température min : "+ event.feature.getProperty("min")+"&deg;C"
                         + "<br /> " + "Température max : "+ event.feature.getProperty("max")+"&deg;C"


                         );
                         var content = infowindow.getContent();
                         var testimonial = document.getElementById('capture');
                         testimonial.innerHTML = content;

                      });
        var kmlLayer = new geoXML3.parser({
                        map: map,

                    });
                    kmlLayer.parse(src);
           }



          var checkIfDataRequested = function() {
              // Stop extra requests being sent
              while (gettingData === true) {
                request.abort();
                gettingData = false;
              }
              getCoords();
            };

            // Get the coordinates from the Map bounds
            var getCoords = function() {
              var bounds = map.getBounds();
              var NE = bounds.getNorthEast();
              var SW = bounds.getSouthWest();
              getWeather(NE.lat(), NE.lng(), SW.lat(), SW.lng());
            };

            // Make the weather request
            var getWeather = function(northLat, eastLng, southLat, westLng) {
              gettingData = true;
              var requestString = "http://api.openweathermap.org/data/2.5/box/city?bbox="
                                  + westLng + "," + northLat + "," //left top
                                  + eastLng + "," + southLat + "," //right bottom
                                  + map.getZoom()
                                  + "&cluster=yes&format=json"
                                  + "&units=metric&lang=fr"
                                  + "&APPID=" + openWeatherMapKey;
              request = new XMLHttpRequest();
              request.onload = proccessResults;
              request.open("get", requestString, true);
              request.send();
            };

            // Take the JSON results and proccess them
            var proccessResults = function() {
              console.log(this);
              var results = JSON.parse(this.responseText);
              if (results.list.length > 0) {
                  resetData();
                  for (var i = 0; i < results.list.length; i++) {
                    geoJSON.features.push(jsonToGeoJson(results.list[i]));
                  }
                  drawIcons(geoJSON);
              }
            };
  var infowindow = new google.maps.InfoWindow();


            // For each result that comes back, convert the data to geoJSON
            var jsonToGeoJson = function (weatherItem) {
              var feature = {
                type: "Feature",
                properties: {
                  city: weatherItem.name,
                  weather: weatherItem.weather[0].main,
                  temperature: weatherItem.main.temp,
                  min: weatherItem.main.temp_min,
                  max: weatherItem.main.temp_max,
                  humidity: weatherItem.main.humidity,
                  pressure: weatherItem.main.pressure,
                  windSpeed: weatherItem.wind.speed,
                  description: weatherItem.weather[0].description,
                  windDegrees: weatherItem.wind.deg,
                  windGust: weatherItem.wind.gust,
                  icon: "http://openweathermap.org/img/w/"
                        + weatherItem.weather[0].icon  + ".png",
                  coordinates: [weatherItem.coord.Lon, weatherItem.coord.Lat]
                },
                geometry: {
                  type: "Point",
                  coordinates: [weatherItem.coord.Lon, weatherItem.coord.Lat]
                }
              };
              // Set the custom marker icon
              map.data.setStyle(function(feature) {
                return {
                  icon: {
                    url: feature.getProperty('icon'),
                    anchor: new google.maps.Point(25, 25)
                  }
                };
              });

              // returns object
              return feature;
            };

            // Add the markers to the map
            var drawIcons = function (weather) {
               map.data.addGeoJson(geoJSON);
               // Set the flag to finished
               gettingData = false;
            };

            // Clear data layer and geoJSON
            var resetData = function () {
              geoJSON = {
                type: "FeatureCollection",
                features: []
              };
              map.data.forEach(function(feature) {
                map.data.remove(feature);
              });
            };

            google.maps.event.addDomListener(window, 'load', initMap);


    </script>

</body>
</html>
