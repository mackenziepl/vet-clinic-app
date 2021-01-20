package com.example.vetclinicapp.dtos;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClientResponse {
    Long id;
    String firstName;
    String lastName;
    String animal;
}
