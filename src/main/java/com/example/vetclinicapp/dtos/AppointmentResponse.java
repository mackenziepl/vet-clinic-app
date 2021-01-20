package com.example.vetclinicapp.dtos;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class AppointmentResponse {
    Long id;
    LocalDate dateOfAppointment;
    LocalTime timeOfAppointment;
    DoctorResponse doctor;
    ClientResponse client;
}
