// RuyaTabirRepository.java
package com.drema.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drema.service.model.Ruya_Tabir_Model;

public interface RuyaTabirRepository extends JpaRepository<Ruya_Tabir_Model, Long> {
    List<Ruya_Tabir_Model> findByUserUserId(Long userId);
}
