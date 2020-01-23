package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantDao extends JpaRepository<Participant,Integer> {
}
