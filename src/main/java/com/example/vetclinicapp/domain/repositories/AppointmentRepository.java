package com.example.vetclinicapp.domain.repositories;

import com.example.vetclinicapp.domain.entities.Appointment;
import com.example.vetclinicapp.domain.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a LEFT JOIN Doctor d ON a.doctor.id = d.id WHERE a.doctor.id = ?1 AND a.dateOfAppointment = ?2")
    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate dateOfAppointment);

    boolean existsByDoctorAndDateOfAppointmentAndTimeOfAppointment(Doctor doctor, LocalDate dateOfAppointment, LocalTime timeOfAppointment);
}
