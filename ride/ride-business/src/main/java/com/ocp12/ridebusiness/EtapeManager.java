package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Etape;
import com.ocp12.ridemodele.Sortie;

import java.util.List;

public interface EtapeManager {
    public void saveEtape(Etape letape,Integer sortieId);
    public List<Etape> findBySortie(Sortie sortie);
    public Etape findById(Integer etapeId);
}
