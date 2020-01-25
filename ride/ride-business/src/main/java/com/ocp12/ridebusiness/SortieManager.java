package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SortieManager {
    public List<Sortie> sortiesList();
    public void saveSortie(Sortie laSortie);
    public  Sortie findById(Integer sortieId);
    public Sortie findByFilename(String kmlname);
    public List<Sortie> organisateurSorties(Utilisateur organisateur);
}
