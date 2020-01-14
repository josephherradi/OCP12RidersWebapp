package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Utilisateur;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface UtilisateurManager {
    public void saveUtilisateur(Utilisateur utilisateur);
    public  Utilisateur findByIdentifiant(String userId);

}
