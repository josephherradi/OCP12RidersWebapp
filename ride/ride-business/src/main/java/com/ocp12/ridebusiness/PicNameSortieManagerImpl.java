package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.PicNameSortieDao;
import com.ocp12.ridemodele.PicNamesSortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PicNameSortieManagerImpl implements PicNameSortieManager {

    @Autowired
    private PicNameSortieDao picNameSortieDao;

    @Override
    public void savePicNameSortie(PicNamesSortie picNamesSortie) {
        picNameSortieDao.save(picNamesSortie);

    }
}
