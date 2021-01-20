package com.example.vetclinicapp.services;

import com.example.vetclinicapp.controllers.exceptions.ResourceNotFoundException;
import com.example.vetclinicapp.controllers.exceptions.ResourceNotUniqueException;
import com.example.vetclinicapp.domain.entities.Appointment;
import com.example.vetclinicapp.domain.repositories.AppointmentRepository;
import com.example.vetclinicapp.domain.repositories.ClientRepository;
import com.example.vetclinicapp.dtos.AppointmentRequest;
import com.example.vetclinicapp.dtos.AppointmentResponse;
import com.example.vetclinicapp.mappers.AppointmentMapper;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.vetclinicapp.services.fixtures.AppointmentFixture.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    private final AppointmentRepository appointmentRepository = mock(AppointmentRepository.class);
    private final ClientRepository clientRepository = mock(ClientRepository.class);

    private final AppointmentService service = new AppointmentServiceImpl(appointmentRepository, clientRepository);

    @Test
    public void shouldReturnAppointmentList_whenCallService_findAll() {
        //Given
        List<Appointment> appointments = appointmentListFixture();
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(AppointmentMapper::mapToAppointmentResponse)
                .collect(Collectors.toList());
        //When
        when(appointmentRepository.findAll()).thenReturn(appointments);
        List<AppointmentResponse> allAppointmentsList = service.findAll();
        //Then
        assertEquals(allAppointmentsList.size(), appointments.size());
        assertEquals(allAppointmentsList.get(0).getId(), appointmentResponses.get(0).getId());
    }

    @Test
    public void shouldCreateAppointmentEntity_whenIncomingRequest() {
        //Given
        AppointmentRequest appointmentRequest = appointmentRequestFixture();
        //When
        when(clientRepository.findByCodeAndPin(appointmentRequest.getCode(), appointmentRequest.getPin()))
                .thenReturn(clientFixture());
        service.create(appointmentRequest);
        //Then
        verify(appointmentRepository, times(1)).save(Mockito.any(Appointment.class));
    }

    @Test(expected = ResourceNotUniqueException.class) //Then
    public void shouldThrownResourceNotUniqueToSqlException_whenRepositoryReturnExistingAppointment() {
        //Given
        when(appointmentRepository.existsByDoctorAndDateOfAppointmentAndTimeOfAppointment(any(), any(), any())).thenReturn(true);
        //When
        service.create(appointmentRequestFixtureWithExistingAppointmentInDatabase());
    }

    @Test(expected = ResourceNotFoundException.class)  //Then
    public void shouldThrownResourceNotFoundException_whenRepositoryReturnEmptyOptional() {
        //Given//When
        appointmentRepository.findById(100L);
        service.delete(100L);
    }

    @Test
    public void shouldDeleteAppointmentEntity_whenIncomingEntity() {
        //Given//When
        appointmentRepository.deleteById(1L);
        //Then
        verify(appointmentRepository, times(1)).deleteById(1L);
    }

}
