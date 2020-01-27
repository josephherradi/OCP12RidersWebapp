package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.EtapeDao;
import com.ocp12.ridemodele.Etape;
import com.ocp12.ridemodele.Sortie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtapeManagerImpl implements EtapeManager {
    @Autowired
    private EtapeDao etapeDao;

    @Override
    public void saveEtape(Etape letape) {
        etapeDao.save(letape);

    }

    @Override
    public List<Etape> findBySortie(Sortie sortie) {
        return etapeDao.findBySortie(sortie);
    }
}
