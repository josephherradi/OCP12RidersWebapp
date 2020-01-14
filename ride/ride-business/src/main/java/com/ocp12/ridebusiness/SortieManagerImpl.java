package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.SortieDao;
import com.ocp12.ridemodele.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortieManagerImpl implements SortieManager{
    @Autowired
    SortieDao sortieDao;

    @Override
    public List<Sortie> sortiesList() {
        return sortieDao.findAll();
    }

    @Override
    public void saveSortie(Sortie laSortie) {
        sortieDao.save(laSortie);

    }
}
