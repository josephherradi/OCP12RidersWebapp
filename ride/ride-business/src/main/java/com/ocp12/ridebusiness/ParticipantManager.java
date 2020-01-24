package com.ocp12.ridebusiness;

import com.ocp12.ridemodele.Participant;
import com.ocp12.ridemodele.Utilisateur;

import java.util.List;

public interface ParticipantManager {
    public List<Participant> participantsList();
    public void saveParticipant(Participant leParticipant);
    public List<Participant> findByUser(Utilisateur utilisateur);

}
