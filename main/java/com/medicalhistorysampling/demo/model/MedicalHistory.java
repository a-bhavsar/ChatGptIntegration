package com.medicalhistorysampling.demo.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="medicalHistory")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="doctor_name")
    private String doctorName;

    @Column(name="department")
    private String department;

    @Column(name="symptoms")
    private String symptoms;

    @Column(name="disease")
    private String disease;

    @Column(name="prescription")
    private String prescription;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @Override
    public String toString() {
        return "MedicalHistory{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", department='" + department + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", disease='" + disease + '\'' +
                ", prescription='" + prescription + '\'' +
                '}';
    }
}
