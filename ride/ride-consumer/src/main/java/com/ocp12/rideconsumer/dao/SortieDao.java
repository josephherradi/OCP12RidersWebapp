package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Sortie;
import com.ocp12.ridemodele.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortieDao extends JpaRepository<Sortie,Integer>{
    @Query("select s from Sortie s where s.filename= :kmlname")
    Sortie findByFilename(@Param("kmlname") String kmlname);

    @Query("select s from Sortie s where s.organisateur= :organizer")
    List<Sortie> findByOrganisateur(@Param("organizer") Utilisateur organisateur);
}
