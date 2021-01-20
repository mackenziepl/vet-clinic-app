package com.example.vetclinicapp.controllers;

import com.example.vetclinicapp.dtos.AppointmentRequest;
import com.example.vetclinicapp.dtos.AppointmentResponse;
import com.example.vetclinicapp.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponse>> findAllAppointments() {
        List<AppointmentResponse> appointments = service.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/appointment/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> findAllAppointmentsByDoctorAndDateOfAppointment(
            @PathVariable Long doctorId,
            @RequestParam(value = "dateOfAppointment") String dateOfAppointment) {
        List<AppointmentResponse> appointments = service.findAllByDoctorIdAndDateOfAppointment(doctorId, dateOfAppointment);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        AppointmentResponse response = service.create(appointmentRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>("Appointment was removed successfully with id: " + id, HttpStatus.OK);
    }
}
