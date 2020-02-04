package com.ocp12.ridebusiness;

import com.ocp12.ridebusiness.businessRules.Brules;
import com.ocp12.rideconsumer.dao.EtapeDao;
import com.ocp12.rideconsumer.dao.SortieDao;
import com.ocp12.ridemodele.Etape;
import com.ocp12.ridemodele.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapeManagerImpl implements EtapeManager {
    @Autowired
    private EtapeDao etapeDao;

    @Autowired
    private SortieDao sortieDao;


    @Override
    public void saveEtape(Etape letape,Integer sortieId) {
        Sortie laSortie=sortieDao.findById(sortieId).orElse(null);
        letape.setSortie(laSortie);
        etapeDao.save(letape);

    }

    @Override
    public List<Etape> findBySortie(Sortie sortie) {
        return etapeDao.findBySortie(sortie);
    }

    @Override
    public Etape findById(Integer etapeId) {
        return etapeDao.findById(etapeId).orElse(null);
    }
}
