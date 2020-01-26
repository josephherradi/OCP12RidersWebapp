package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Etape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtapeDao extends JpaRepository<Etape,Integer> {
}
