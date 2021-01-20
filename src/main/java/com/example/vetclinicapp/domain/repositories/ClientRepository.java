package com.example.vetclinicapp.domain.repositories;

import com.example.vetclinicapp.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByCodeAndPin(String code, String pin);
}
