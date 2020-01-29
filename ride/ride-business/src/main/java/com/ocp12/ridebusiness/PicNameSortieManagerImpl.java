package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.PicNameSortieDao;
import com.ocp12.ridemodele.Picnamessortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PicNameSortieManagerImpl implements PicNameSortieManager {

    @Autowired
    private PicNameSortieDao picNameSortieDao;

    @Override
    public void savePicNameSortie(Picnamessortie picNamesSortie) {
        picNameSortieDao.save(picNamesSortie);

    }

    @Override
    public List<Picnamessortie> findBySortieId(Integer sortieId) {
        return picNameSortieDao.findBySortieId(sortieId);
    }

    @Override
    public Picnamessortie findById(Integer picId) {
        return picNameSortieDao.findById(picId).orElse(null);
    }
}
