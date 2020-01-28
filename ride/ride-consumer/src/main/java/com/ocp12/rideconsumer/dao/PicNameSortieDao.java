package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.PicNamesSortie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicNameSortieDao extends JpaRepository<PicNamesSortie,Integer> {
}
