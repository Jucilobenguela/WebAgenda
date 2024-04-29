package com.benguela.webAgendaAPI.repository;

import com.benguela.webAgendaAPI.model.ServiceProvided;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided,Long> {
    ServiceProvided findByName(String name);
}
