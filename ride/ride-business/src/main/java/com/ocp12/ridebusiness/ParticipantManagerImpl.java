package com.ocp12.ridebusiness;

import com.ocp12.ridebusiness.exceptions.FunctionalException;
import com.ocp12.rideconsumer.dao.ParticipantDao;
import com.ocp12.ridemodele.Participant;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ParticipantManagerImpl implements ParticipantManager {
    @Autowired
    private ParticipantDao participantDao;

    @Autowired
    private SortieManager sortieManager;

    @Override
    public List<Participant> participantsList() {
        return participantDao.findAll();
    }

    @Override
    public void saveParticipant(Participant leParticipant) {
        this.checkParticipantsNumber(leParticipant);
        participantDao.save(leParticipant);
    }

    @Override
    public List<Participant> findByUser(Utilisateur utilisateur) {
        return participantDao.findByUtilisateur(utilisateur);
    }

    @Override
    public void deleteParticipant(Integer participantId) {
        participantDao.deleteById(participantId);
    }

    @Override
    public Participant findById(Integer participantId) {
        return participantDao.findById(participantId).orElse(null);
    }

    @Override
    public Participant findByUtilisateurAndSortie(Utilisateur utilisateur, Sortie sortie) {
        return participantDao.findByUtilisateurAndSortie(utilisateur,sortie);
    }

    @Override
    public List<Participant> findBySortieId(Integer sortieId) {
        return participantDao.findBySortieId(sortieId);
    }

    public void checkParticipantsNumber(Participant participant) throws FunctionalException {
        Integer sortieId=participant.getSortie().getSortieId();
        Integer participantsNumber=participantDao.findBySortieId(sortieId).size();
        Integer limitNumberParticipants=sortieManager.findById(sortieId).getNbrParticipants();
        if(participantsNumber>=limitNumberParticipants){
            throw new FunctionalException("Nombre max de participants atteints :(");
            }
        }




    }



