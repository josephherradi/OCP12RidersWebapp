package com.ocp12.rideconsumer.dao;

import com.ocp12.ridemodele.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatDao extends JpaRepository<Chat,Integer> {

}
