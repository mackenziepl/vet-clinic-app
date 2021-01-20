# Maciej Kopeć - recruiting task - "Vet Clinic app"

## What you'll build
- A simple Spring Boot REST application with H2

## Stack
- Java 8
- Spring Boot 2+
- Hibernate
- H2
- Maven

## Run
- Access to http://localhost:8080/api/v1/appointments
- Database H2 - http://localhost:8080/h2-console



Examples endpoints:
POST - http://localhost:8080/api/v1/appointment 

{
    "dateOfAppointment": "2021-02-02",
    "timeOfAppointment": "14:30:00",
    "doctor": {
        "id": 1,
        "firstName": "Stefan",
        "lastName": "Nowak"
    },
    "code": "0003",
    "pin": "0033"
}

GET - http://localhost:8080/api/v1/appointment/doctor/1?dateOfAppointment=2021-02-02