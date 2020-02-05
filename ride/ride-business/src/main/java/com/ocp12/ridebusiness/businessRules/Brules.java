package com.ocp12.ridebusiness.businessRules;

import com.ocp12.ridebusiness.ParticipantManager;
import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.ridebusiness.exceptions.FunctionalException;
import com.ocp12.ridemodele.Participant;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Component
public class Brules {
    @Autowired
    private SortieManager sortieManager;

    @Autowired
    private ParticipantManager participantManager;

    public void checkUserComment(Utilisateur loggedUser, Integer sortieId){
        List<Participant> participantsSortie=sortieManager.findById(sortieId).getParticipants();
        List<Participant> foundUser=new ArrayList<>();
        for(Participant participant: participantsSortie){
            if(participant.getUtilisateur().getIdentifiant().equals(loggedUser.getIdentifiant())){
                foundUser.add(participant);
            }

        }

        if(foundUser.size()==0){
            throw new FunctionalException("non autorisé");
        }
    }

    public void checkSortieStatut(Integer sortieId){
        Sortie sortie=sortieManager.findById(sortieId);
        String statut=sortie.getStatut();

        if(!statut.equalsIgnoreCase("Termine")){
            throw new FunctionalException("Action possible quand le statut de la sortie est termine");
        }
    }



    public void checkUserParticipant(Utilisateur loggedUser, Integer participantId){
        Utilisateur participantUser=participantManager.findById(participantId).getUtilisateur();
        if(!loggedUser.getIdentifiant().equals(participantUser.getIdentifiant())){
            throw new FunctionalException("non autorisé");
        }
    }

    public void checkUserOrganisateur(Utilisateur loggedUser, HttpSession session, Integer sortieId){
        Utilisateur organisateurUser=sortieManager.findById(sortieId).getOrganisateur();
        if(!loggedUser.getIdentifiant().equals(organisateurUser.getIdentifiant())){
            throw new FunctionalException("non autorisé");
        }

    }
}
