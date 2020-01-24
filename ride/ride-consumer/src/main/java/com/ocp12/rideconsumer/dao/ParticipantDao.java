package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Participant;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantDao extends JpaRepository<Participant,Integer> {
    @Query("select p from Participant p where p.utilisateur= :user")
    List<Participant> findByUtilisateur(@Param("user") Utilisateur utilisateur);
}
