package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Picnamessortie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicNameSortieDao extends JpaRepository<Picnamessortie,Integer> {
    @Query(value = "select p from Picnamessortie p where p.sortieId= :idsortie")
    List<Picnamessortie> findBySortieId(@Param("idsortie") Integer sortieId);
}
