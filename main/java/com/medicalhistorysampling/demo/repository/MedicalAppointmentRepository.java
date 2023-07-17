package com.medicalhistorysampling.demo.repository;

import com.medicalhistorysampling.demo.model.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Integer> {

}
