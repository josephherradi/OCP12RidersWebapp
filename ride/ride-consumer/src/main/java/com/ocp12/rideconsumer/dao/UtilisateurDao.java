package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Utilisateur;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer> {
    @Query("select u from Utilisateur u where u.identifiant= :userId")
    Optional<Utilisateur> findByIdentifiant(@Param("userId") String identifiant);

}
