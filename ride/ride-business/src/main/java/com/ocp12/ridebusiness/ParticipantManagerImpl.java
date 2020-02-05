package com.ocp12.ridebusiness;

import com.ocp12.ridebusiness.businessRules.Brules;
import com.ocp12.rideconsumer.dao.ParticipantDao;
import com.ocp12.ridemodele.Participant;
import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipantManagerImpl implements ParticipantManager {
    @Autowired
    private ParticipantDao participantDao;

    @Autowired
    private SortieManager sortieManager;

    @Autowired
    private Brules brules;

    @Override
    public List<Participant> participantsList() {
        return participantDao.findAll();
    }

    @Override
    public void saveParticipant(Participant leParticipant) {
        brules.checkParticipantsNumber(leParticipant);
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






    }



