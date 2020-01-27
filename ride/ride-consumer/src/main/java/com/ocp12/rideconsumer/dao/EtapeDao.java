package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Etape;
import com.ocp12.ridemodele.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtapeDao extends JpaRepository<Etape,Integer> {
    @Query("select p from Etape p where p.sortie= :sortieBean")
    List<Etape> findBySortie(@Param("sortieBean")Sortie sortie);
}
