package com.drema.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drema.service.model.Ruya_User_Model;

public interface RuyaUserRepository extends JpaRepository<Ruya_User_Model, Long> {
 Optional<Ruya_User_Model> findByUserNameAndUserPass(String userName, String userPass);
}

