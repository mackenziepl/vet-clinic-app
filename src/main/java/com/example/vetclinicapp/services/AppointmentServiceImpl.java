package com.example.vetclinicapp.services;

import com.example.vetclinicapp.controllers.exceptions.ResourceNotFoundException;
import com.example.vetclinicapp.controllers.exceptions.ResourceNotUniqueException;
import com.example.vetclinicapp.domain.entities.Appointment;
import com.example.vetclinicapp.domain.entities.Client;
import com.example.vetclinicapp.domain.repositories.AppointmentRepository;
import com.example.vetclinicapp.domain.repositories.ClientRepository;
import com.example.vetclinicapp.dtos.AppointmentRequest;
import com.example.vetclinicapp.dtos.AppointmentResponse;
import com.example.vetclinicapp.mappers.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;

    @Override
    public List<AppointmentResponse> findAll() {
        return appointmentRepository.findAll().stream()
                .map(AppointmentMapper::mapToAppointmentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentResponse> findAllByDoctorIdAndDateOfAppointment(Long doctorId, String dateOfAppointment) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, LocalDate.parse(dateOfAppointment)).stream()
                .map(AppointmentMapper::mapToAppointmentResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AppointmentResponse create(AppointmentRequest appointmentRequest) {
        Client client = clientRepository.findByCodeAndPin(appointmentRequest.getCode(), appointmentRequest.getPin());
        Appointment appointment = AppointmentMapper.mapRequestToEntity(appointmentRequest, client);
        if (!appointmentRepository.existsByDoctorAndDateOfAppointmentAndTimeOfAppointment(appointment.getDoctor(),
                appointment.getDateOfAppointment(), appointment.getTimeOfAppointment())) {
            appointmentRepository.save(appointment);
        } else throw new ResourceNotUniqueException("There is already exist an appointment on this time !");

        return AppointmentMapper.mapToAppointmentResponse(appointment);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment with id: " + id + " not found"));
        appointmentRepository.delete(appointment);
    }
}
