package com.ocp12.ridebusiness.businessRules;

import com.ocp12.ridebusiness.ParticipantManager;
import com.ocp12.ridebusiness.SortieManager;
import com.ocp12.ridebusiness.UtilisateurManager;
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

    @Autowired
    private UtilisateurManager utilisateurManager;

//RG1 seuls les participants peuvent commenter la sortie
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
//RG2 actions interdites si le statut de la sortie est différent de terminé
    public void checkSortieStatut(Integer sortieId){
        Sortie sortie=sortieManager.findById(sortieId);
        String statut=sortie.getStatut();

        if(!statut.equalsIgnoreCase("Termine")){
            throw new FunctionalException("Action possible quand le statut de la sortie est termine");
        }
    }


//RG3 seuls les participants à la sortie peuvent modifier le statut de leur participation et se désister
    public void checkUserParticipant(Utilisateur loggedUser, Integer participantId){
        Utilisateur participantUser=participantManager.findById(participantId).getUtilisateur();
        if(!loggedUser.getIdentifiant().equals(participantUser.getIdentifiant())){
            throw new FunctionalException("non autorisé");
        }
    }
//RG4 seul l'oganisateur de la sortie est autorisé à modifier le statut de la sortie, uploader des photos et commenter
//le roadbook (liste des etapes)
    public void checkUserOrganisateur(Utilisateur loggedUser, Integer sortieId){
        Utilisateur organisateurUser=sortieManager.findById(sortieId).getOrganisateur();
        if(!loggedUser.getIdentifiant().equals(organisateurUser.getIdentifiant())){
            throw new FunctionalException("non autorisé");
        }

    }
//RG5 Verifie la limite du nombre de participants
    public void checkParticipantsNumber(Participant participant) throws FunctionalException {
        Integer sortieId=participant.getSortie().getSortieId();
        Integer participantsNumber=participantManager.findBySortieId(sortieId).size();
        Integer limitNumberParticipants=sortieManager.findById(sortieId).getNbrParticipants();
        if(participantsNumber>=limitNumberParticipants){
            throw new FunctionalException("Nombre max de participants atteints :(");
        }
    }
// RG6 Une personne ne peut pas s'inscrire plusieurs fois ...
    public void checkAlreadJoinedSortie(Participant participant){
        Participant participantFound=participantManager.findByUtilisateurAndSortie(participant.getUtilisateur(),participant.getSortie());
        if(participantFound!=null){
            throw new FunctionalException("Vous êtes déjà inscrit comme participant à la sortie");
        }
    }
// RG7 Impossible de participer si la statut est au statut terminé
    public void checkStatutSortieBeforeJoinParticipant(Participant participant){
        if(participant.getSortie().getStatut().equalsIgnoreCase("Termine") || participant.getSortie().getStatut().equalsIgnoreCase("Annule")){
            throw new FunctionalException("Impossible de participer à une sortie terminée");
        }

    }
//RG8 Seuls les membres connectés peuvent poster des messages sur le fil de chat.
    public void checkRegistredUser(String userId){
        Utilisateur utilisateur=utilisateurManager.findByIdentifiant(userId);

        if (utilisateur==null){
            throw new FunctionalException("Vous devez créer un compte et vous connecter d'abord");
        }

    }
}
