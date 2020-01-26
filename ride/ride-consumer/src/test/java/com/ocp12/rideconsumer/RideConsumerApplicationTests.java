package com.ocp12.rideconsumer;



import com.ocp12.rideconsumer.kmlparser.KmlParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


public class RideConsumerApplicationTests {
    String folder = "/Users/mobileapptechnologies/OCP12/OCP12RidersWebapp/ride/ride-consumer/tour-france.kml";
    @Test
    public void parseKml() throws FileNotFoundException {
    new KmlParser().runParser(folder);
    }


}