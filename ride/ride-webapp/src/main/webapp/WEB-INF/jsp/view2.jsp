<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>liste livres</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
 <style>
      #map {
       height: 600px;
       width: 600px;
       overflow: hidden;
       float: left;
       border: thin solid #333;
       }
      #capture {
       height: 360px;
       width: 480px;
       overflow: hidden;
       float: left;
       background-color: #ECECFB;
       border: thin solid #333;
       border-left: none;
       }
    </style>

</head>
<body>
	<div class="container">
		<div class="col-md-offset-1 col-md-11">

			<div class="panel-body">
			<div id="map"></div>
			<br />
			<div id="capture"></div>


			</div>

		</div>
	</div>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script async defer
src="https://maps.googleapis.com/maps/api/js?key=&callback=initMap">
</script>

<script>
      var map;
      var src = 'https://raw.githubusercontent.com/josephherradi/OCP12RidersWebapp/master/ride/ride-consumer/demo3.kml';

      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          zoom: 2,
          mapTypeId: 'roadmap'
        });

        var kmlLayer = new google.maps.KmlLayer(src, {
          suppressInfoWindows: true,
          preserveViewport: false,
          map: map
        });
        kmlLayer.addListener('click', function(event) {
          var content = event.featureData.infoWindowHtml;
          var testimonial = document.getElementById('capture');
          testimonial.innerHTML = content;
        });
      }
    </script>

</body>
</html>