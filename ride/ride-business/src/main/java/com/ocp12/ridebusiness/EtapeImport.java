package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.kmlparser.CoordinatesBean;
import com.ocp12.rideconsumer.kmlparser.DistanceCalculator;
import com.ocp12.rideconsumer.kmlparser.KmlParser;
import com.ocp12.ridemodele.Etape;
import com.ocp12.ridemodele.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;

@Component
public class EtapeImport {
    @Autowired
    private EtapeManager etapeManager;

    public  Integer processor(String folder, Sortie laSortie) throws FileNotFoundException {


        List<CoordinatesBean> placemarksList=new KmlParser().runParser(folder);
        Integer Nmarkers=placemarksList.size()-1;

        for (int i = 0; i < placemarksList.size()-1; i++) {
            Etape etape =new Etape();
            if(i==0){
                etape.setDistance(0.0);
            }else{
                Double distance=new DistanceCalculator().distance(placemarksList.get(i).getLatitude(),placemarksList.get(i).getLongiture(),placemarksList.get(i-1).getLatitude(),placemarksList.get(i-1).getLongiture(),"K");
                etape.setDistance(distance);
            }
            etape.setNom(placemarksList.get(i).getPlaceMarkName());
            etape.setLatitude(placemarksList.get(i).getLatitude());
            etape.setLongitude(placemarksList.get(i).getLongiture());
            etapeManager.saveEtape(etape,laSortie.getSortieId());
        }
    return Nmarkers;
    }

}


