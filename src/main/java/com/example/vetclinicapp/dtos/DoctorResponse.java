package com.example.vetclinicapp.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DoctorResponse {
    Long id;
    String firstName;
    String lastName;
}
