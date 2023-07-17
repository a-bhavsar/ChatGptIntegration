package com.medicalhistorysampling.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class MedicalAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="age")
    private int age;
    @Column(name="gender")
    private String gender;
    @Column(name="blood_group")
    private String bloodGroup;
    @Column(name="symptoms")
    private String symptoms;
    @Column(name="prescription")
    private String prescription;
    @Column(name="disease")
    private String disease;
    @Column(name="department")
    private String department;
    @Column(name="next_schedule")
    private Date nextSchedule;
    @Column(name="recommended_food")
    private String recommendedFood;
    @Column(name="restricted_food")
    private String restrictedFood;
    @Column(name="blood_pressure")
    private BigDecimal bloodPressure;
    @Column(name="diabetes_level")
    private BigDecimal diabetesLevel;
}
