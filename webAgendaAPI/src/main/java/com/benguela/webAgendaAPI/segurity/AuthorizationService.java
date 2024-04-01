package com.benguela.webAgendaAPI.segurity;

import com.benguela.webAgendaAPI.model.User;
import com.benguela.webAgendaAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
   UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException  {
        UserDetails user = userRepository.findByEmail(userEmail);
        if (user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }

}
