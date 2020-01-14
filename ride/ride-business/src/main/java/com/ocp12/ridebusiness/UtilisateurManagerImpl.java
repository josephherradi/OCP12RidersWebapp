package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.UtilisateurDao;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service

public class UtilisateurManagerImpl implements UtilisateurManager {

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Override
    public void saveUtilisateur(Utilisateur utilisateur) {
    utilisateurDao.save(utilisateur);
        }

    @Override
    public Utilisateur findByIdentifiant(String userId) {
        return utilisateurDao.findByIdentifiant(userId).orElse(null);
    }
}
