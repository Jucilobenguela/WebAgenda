package com.benguela.webAgendaAPI.segurity;
import com.benguela.webAgendaAPI.exception.NotAuthenticateException;
import com.benguela.webAgendaAPI.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    public Authentication authenticate(String email, String password) {
        Authentication userNamePassword = new UsernamePasswordAuthenticationToken(email, password);
        return this.authenticationManager.authenticate(userNamePassword);
    }
}

