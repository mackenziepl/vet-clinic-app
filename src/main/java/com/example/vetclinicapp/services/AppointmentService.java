package com.example.vetclinicapp.services;

import com.example.vetclinicapp.dtos.AppointmentRequest;
import com.example.vetclinicapp.dtos.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    List<AppointmentResponse> findAll();

    List<AppointmentResponse> findAllByDoctorIdAndDateOfAppointment(Long doctorId, String dateOfAppointment);

    AppointmentResponse create(AppointmentRequest appointmentRequest);

    void delete(Long id);
}
