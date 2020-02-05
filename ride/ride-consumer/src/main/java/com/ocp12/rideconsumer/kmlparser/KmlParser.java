package com.ocp12.rideconsumer.kmlparser;


import de.micromata.opengis.kml.v_2_2_0.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


 public class KmlParser {



    public static List<CoordinatesBean> runParser(String folder) throws FileNotFoundException {
        List<CoordinatesBean> coordinatesBeanList=new ArrayList<>();
        File file = new File(folder);
        InputStream inputStream = new FileInputStream(file);
        Kml kml = Kml.unmarshal(inputStream);
        Feature feature = kml.getFeature();
        coordinatesBeanList=parseFeature(feature);
        return coordinatesBeanList;

    }

    private static List<CoordinatesBean> parseFeature(Feature feature) {
        List<CoordinatesBean> coordinatesBeanList=new ArrayList<>();
       try {
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
                           CoordinatesBean coordinatesBean = parseGeometry(geometry, placemark);
                           coordinatesBeanList.add(coordinatesBean);

                       }
                   }
               }
           }
       }catch (java.lang.ClassCastException e){
           System.out.println("pas de placemark");
       }
        return coordinatesBeanList;
    }


    private static CoordinatesBean parseGeometry(Geometry geometry, Placemark placemark) {
        CoordinatesBean coordinatesBean=new CoordinatesBean();
        if (geometry != null) {
            if (geometry instanceof Point) {
                Point point = (Point) geometry;
                List<Coordinate> coordinates = point.getCoordinates();
                if (coordinates != null) {
                    for (Coordinate coordinate : coordinates) {
                       coordinatesBean=parseCoordinate(coordinate,placemark);
                    }
                }
            }
        }
        return coordinatesBean;
    }



    private static CoordinatesBean parseCoordinate(Coordinate coordinate,Placemark placemark) {
        CoordinatesBean coordinatesBean=new CoordinatesBean();
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
