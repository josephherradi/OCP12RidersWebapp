package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.EtapeDao;
import com.ocp12.ridemodele.Etape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtapeManagerImpl implements EtapeManager {
    @Autowired
    private EtapeDao etapeDao;

    @Override
    public void saveEtape(Etape letape) {
        etapeDao.save(letape);

    }
}
