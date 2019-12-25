package com.webapp.rideconsumer;



import com.webapp.rideconsumer.kmlparser.KmlParser;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;


public class RideConsumerApplicationTests {

    @Test
    public void parseKml() throws FileNotFoundException {
    new KmlParser().runParser();
    }


}