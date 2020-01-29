package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Picnamessortie;

import java.util.List;

public interface PicNameSortieManager {
    public void savePicNameSortie(Picnamessortie picNamesSortie);
    public List<Picnamessortie> findBySortieId(Integer sortieId);
    public Picnamessortie findById(Integer picId);
}
