package com.ocp12.ridebusiness;

import com.ocp12.rideconsumer.dao.ParticipantDao;
import com.ocp12.ridemodele.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantManagerImpl implements ParticipantManager {
    @Autowired
    private ParticipantDao participantDao;

    @Override
    public List<Participant> participantsList() {
        return participantDao.findAll();
    }

    @Override
    public void saveParticipant(Participant leParticipant) {
        participantDao.save(leParticipant);
    }
}
