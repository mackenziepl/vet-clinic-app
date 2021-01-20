package com.example.vetclinicapp.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "uuid")

@Entity
@Table(name = "doctors")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String uuid = UUID.randomUUID().toString();
    @Column(nullable = false)
    @Size(max = 30)
    private String firstName;
    @Column(nullable = false)
    @Size(max = 30)
    private String lastName;
    @OneToMany(targetEntity = Appointment.class,
            mappedBy = "doctor",
            orphanRemoval = true,
            cascade = CascadeType.PERSIST)
    private List<Appointment> appointments = new ArrayList<>();
}
