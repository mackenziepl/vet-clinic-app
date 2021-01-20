package com.example.vetclinicapp.dtos;

import com.example.vetclinicapp.domain.entities.Doctor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class AppointmentRequest {
    @NotNull
    LocalDate dateOfAppointment;
    @NotNull
    LocalTime timeOfAppointment;
    @NotNull
    Doctor doctor;
    @NotNull
    @Size(max = 4, min = 4)
    String code;
    @NotNull
    @Size(max = 4, min = 4)
    String pin;
}
