package com.ocp12.rideconsumer.kmlparser;


import de.micromata.opengis.kml.v_2_2_0.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component

public class KmlParser {
    CoordinatesBean coordinatesBean=new CoordinatesBean();
    List<CoordinatesBean> coordinatesBeanList=new ArrayList<>();


    public List<CoordinatesBean> runParser() throws FileNotFoundException {

        String path = "/Users/mobileapptechnologies/OCP12/OCP12RidersWebapp/ride/ride-consumer/tour-france.kml";
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        Kml kml = Kml.unmarshal(inputStream);
        Feature feature = kml.getFeature();
        List<CoordinatesBean> coordinatesBeanList=parseFeature(feature);
        return coordinatesBeanList;

    }

    private List<CoordinatesBean> parseFeature(Feature feature) {
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
                        List<CoordinatesBean>coordinatesBeanList=parseGeometry(geometry,placemark);
                    }
                }
            }
        }
        return coordinatesBeanList;
    }


    private List<CoordinatesBean> parseGeometry(Geometry geometry, Placemark placemark) {
        if (geometry != null) {
            if (geometry instanceof Point) {
                Point point = (Point) geometry;
                List<Coordinate> coordinates = point.getCoordinates();
                if (coordinates != null) {
                    for (Coordinate coordinate : coordinates) {
                       coordinatesBeanList.add(parseCoordinate(coordinate,placemark));
                    }
                }
            }
        }
        return coordinatesBeanList;
    }



    private CoordinatesBean parseCoordinate(Coordinate coordinate,Placemark placemark) {
        if(coordinate != null) {
            coordinatesBean.setPlaceMarkName(placemark.getName());
            coordinatesBean.setLatitude(coordinate.getLatitude());
            coordinatesBean.setLongiture(coordinate.getLongitude());
            System.out.println("Nom: " +  placemark.getName());
            System.out.println("Longitude: " +  coordinate.getLongitude());
            System.out.println("Latitude : " +  coordinate.getLatitude());
            System.out.println("");
        }
        return coordinatesBean;
    }

}
