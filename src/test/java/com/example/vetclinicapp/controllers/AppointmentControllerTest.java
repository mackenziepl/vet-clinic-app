package com.example.vetclinicapp.controllers;

import com.example.vetclinicapp.dtos.AppointmentRequest;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.example.vetclinicapp.services.fixtures.AppointmentFixture.*;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;

public class AppointmentControllerTest extends AbstractControllerIntegrationTest {

    private static final String APPOINTMENTS_URL = BASE_API_URL + "/appointments";
    private static final String APPOINTMENT_URL = BASE_API_URL + "/appointment";

    @Test
    public void shouldReturnAppointmentList_whenCallGetAppointmentsApi_withStatus200() {
        when()
                .get(APPOINTMENTS_URL)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturnCreatedAppointment_whenCallPostAppointmentsApi_withStatus201() {
        AppointmentRequest appointmentRequest = createAppointmentRequestFixture();
        given()
                .body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturnConflictAppointment_whenCallPostAppointmentsApi_withExistingAppointmentInDatabase() {
        AppointmentRequest appointmentRequest = createAppointmentRequestFixture();
        given()
                .body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL);

        given()
                .body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL)
                .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    public void shouldReturnBadRequest_whenCallPostAppointmentsApi_withRequestWithoutDateOfAppointment() {
        AppointmentRequest appointmentRequest = appointmentRequestFixtureWith(null, LocalTime.parse("12:00"), doctorFixture(), "0011", "1100");
        given().body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnBadRequest_whenCallPostAppointmentsApi_withRequestWithoutTimeOfAppointment() {
        AppointmentRequest appointmentRequest = appointmentRequestFixtureWith(LocalDate.now().plusDays(3), null, doctorFixture(), "0011", "1100");
        given().body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnBadRequest_whenCallPostAppointmentsApi_withRequestWithoutDoctor() {
        AppointmentRequest appointmentRequest = appointmentRequestFixtureWith(LocalDate.now().plusDays(3), LocalTime.parse("12:00"), null, "0011", "1100");
        given().body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnBadRequest_whenCallPostAppointmentsApi_withRequestWithoutCode() {
        AppointmentRequest appointmentRequest = appointmentRequestFixtureWith(LocalDate.now().plusDays(3), LocalTime.parse("12:00"), doctorFixture(), null, "1100");
        given().body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturnBadRequest_whenCallPostAppointmentsApi_withRequestWithoutPin() {
        AppointmentRequest appointmentRequest = appointmentRequestFixtureWith(LocalDate.now().plusDays(3), LocalTime.parse("12:00"), doctorFixture(), "0011", null);
        given().body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldDeletedAppointment_whenCallDeleteAppointmentsApi_withStatus200() {
        AppointmentRequest appointmentRequest = createAppointmentRequestFixture();
        given()
                .body(appointmentRequest)
                .contentType(ContentType.JSON)
                .when()
                .post(APPOINTMENT_URL);
        when()
                .delete(APPOINTMENT_URL + "/2")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn404_whenCallFindAppointmentByIdToDelete_withNonExistsId() {
        when()
                .delete(APPOINTMENT_URL + "/100")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
