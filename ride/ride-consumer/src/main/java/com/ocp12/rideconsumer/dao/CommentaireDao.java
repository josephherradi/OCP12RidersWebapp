package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentaireDao extends JpaRepository<Commentaire,Integer> {
    @Query("select c from Commentaire c where c.participant.sortie.sortieId= :idSortie")
    public List<Commentaire> findBySortie(@Param("idSortie") Integer sortieId);
}
