package com.benguela.webAgendaAPI.repository;

import com.benguela.webAgendaAPI.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
     Client findByClientPhoneNumber(String phoneNumber);
}
