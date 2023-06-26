package com.medicalhistorysampling.demo.repository;

import com.medicalhistorysampling.demo.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

}
