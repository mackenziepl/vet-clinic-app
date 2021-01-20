package com.example.vetclinicapp.services.fixtures;

import com.example.vetclinicapp.domain.entities.Appointment;
import com.example.vetclinicapp.domain.entities.Client;
import com.example.vetclinicapp.domain.entities.Doctor;
import com.example.vetclinicapp.dtos.AppointmentRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppointmentFixture {

    public static List<Appointment> appointmentListFixture() {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointmentFixture());
        return appointments;
    }

    public static Appointment appointmentFixture() {
        return Appointment.builder()
                .id(1L)
                .dateOfAppointment(LocalDate.now().plusDays(5))
                .timeOfAppointment(LocalTime.parse("12:00"))
                .doctor(doctorFixture())
                .client(clientFixture())
                .build();
    }

    public static AppointmentRequest appointmentRequestFixture() {
        return AppointmentRequest.builder()
                .dateOfAppointment(LocalDate.now().plusDays(3))
                .timeOfAppointment(LocalTime.parse("11:00"))
                .doctor(doctorFixture())
                .code("1111")
                .pin("0101")
                .build();
    }

    public static AppointmentRequest createAppointmentRequestFixture() {
        return AppointmentRequest.builder()
                .dateOfAppointment(LocalDate.now().plusDays(3))
                .timeOfAppointment(LocalTime.parse("11:00"))
                .doctor(doctorFixture())
                .code("0001")
                .pin("0011")
                .build();
    }

    public static AppointmentRequest appointmentRequestFixtureWithExistingAppointmentInDatabase() {
        return AppointmentRequest.builder()
                .dateOfAppointment(LocalDate.now().plusDays(3))
                .timeOfAppointment(LocalTime.parse("11:00"))
                .doctor(doctorFixture())
                .code("1111")
                .pin("0101")
                .build();
    }

    public static AppointmentRequest appointmentRequestFixtureWith(LocalDate date, LocalTime time,
                                                                   Doctor doctor, String code, String pin) {
        return AppointmentRequest.builder()
                .dateOfAppointment(date)
                .timeOfAppointment(time)
                .doctor(doctor)
                .code(code)
                .pin(pin)
                .build();
    }

    public static Doctor doctorFixture() {
        return Doctor.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .appointments(Collections.emptyList())
                .build();
    }

    public static Client clientFixture() {
        return Client.builder()
                .id(1L)
                .firstName("Stefan")
                .lastName("Nowak")
                .animal("Kot")
                .code("1111")
                .pin("0101")
                .appointments(Collections.emptyList())
                .build();
    }
}
