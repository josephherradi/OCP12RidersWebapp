package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Sortie;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SortieManager {
    public List<Sortie> sortiesList();
    public void saveSortie(Sortie laSortie);
}