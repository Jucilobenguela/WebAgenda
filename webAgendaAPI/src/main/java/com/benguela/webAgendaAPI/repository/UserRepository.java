package com.benguela.webAgendaAPI.repository;

import com.benguela.webAgendaAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    UserDetails findByEmail(String userEmail);
}
