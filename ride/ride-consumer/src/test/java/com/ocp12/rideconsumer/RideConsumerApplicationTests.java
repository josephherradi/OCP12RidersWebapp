package com.ocp12.rideconsumer;



import com.ocp12.rideconsumer.kmlparser.KmlParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


public class RideConsumerApplicationTests {

    @Test
    public void parseKml() throws FileNotFoundException {
    new KmlParser().runParser();
    }


}