package com.example.vetclinicapp.mappers;

import com.example.vetclinicapp.domain.entities.Appointment;
import com.example.vetclinicapp.domain.entities.Client;
import com.example.vetclinicapp.dtos.AppointmentRequest;
import com.example.vetclinicapp.dtos.AppointmentResponse;
import com.example.vetclinicapp.dtos.ClientResponse;
import com.example.vetclinicapp.dtos.DoctorResponse;

public class AppointmentMapper {

    public static Appointment mapRequestToEntity(AppointmentRequest request, Client client) {
        return Appointment.builder()
                .dateOfAppointment(request.getDateOfAppointment())
                .timeOfAppointment(request.getTimeOfAppointment())
                .doctor(request.getDoctor())
                .client(client)
                .build();
    }

    public static AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        final DoctorResponse doctorResponse = DoctorResponse.builder()
                .id(appointment.getDoctor().getId())
                .firstName(appointment.getDoctor().getFirstName())
                .lastName(appointment.getDoctor().getLastName())
                .build();
        final ClientResponse clientResponse = ClientResponse.builder()
                .id(appointment.getClient().getId())
                .firstName(appointment.getClient().getFirstName())
                .lastName(appointment.getClient().getLastName())
                .animal(appointment.getClient().getAnimal())
                .build();

        return AppointmentResponse.builder()
                .id(appointment.getId())
                .dateOfAppointment(appointment.getDateOfAppointment())
                .timeOfAppointment(appointment.getTimeOfAppointment())
                .doctor(doctorResponse)
                .client(clientResponse)
                .build();
    }
}
