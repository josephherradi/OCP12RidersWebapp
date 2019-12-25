package com.webapp.rideconsumer.kmlparser;


import de.micromata.opengis.kml.v_2_2_0.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class KmlParser {
    public void runParser() throws FileNotFoundException {
        String path = "/Users/mobileapptechnologies/OCP12/ride/ride-consumer/tour-france.kml";
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        Kml kml = Kml.unmarshal(inputStream);
        Feature feature = kml.getFeature();
        parseFeature(feature);

    }

    private static void parseFeature(Feature feature) {
        if (feature != null) {
            Document document = (Document) feature;
            List<Feature> featureList = document.getFeature();
            for (Feature documentFeature : featureList) {
                Folder folder = (Folder) documentFeature;
                List<Feature> folderfeaturList = folder.getFeature();
                for (Feature folderfeature : folderfeaturList) {
                    if (folderfeature instanceof Placemark) {
                        Placemark placemark = (Placemark) folderfeature;
                        Geometry geometry = placemark.getGeometry();
                        parseGeometry(geometry);
                    }
                }
            }
        }
    }


    private static void parseGeometry(Geometry geometry) {
        if (geometry != null) {
            if (geometry instanceof Point) {
                Point point = (Point) geometry;
                List<Coordinate> coordinates = point.getCoordinates();
                if (coordinates != null) {
                    for (Coordinate coordinate : coordinates) {
                        parseCoordinate(coordinate);
                    }
                }
            }
        }
    }



    private static void parseCoordinate(Coordinate coordinate) {
        if(coordinate != null) {
            System.out.println("Longitude: " +  coordinate.getLongitude());
            System.out.println("Latitude : " +  coordinate.getLatitude());
            System.out.println("Altitude : " +  coordinate.getAltitude());
            System.out.println("");
        }
    }

}
