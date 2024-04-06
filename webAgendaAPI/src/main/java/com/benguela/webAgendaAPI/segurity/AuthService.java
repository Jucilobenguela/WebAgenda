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
    @Autowired
    private TokenService tokenService;
    public String getUserAuthenticatedEmail() throws NotAuthenticateException {
        Authentication authentication = getUserAuthentication();
        if (authentication.isAuthenticated() && authentication instanceof UsernamePasswordAuthenticationToken) {
            User user = (User) authentication.getPrincipal();
            if (!user.getUsername().equals("anonymousUser")) {
                return user.getEmail();
            }
        }
        throw new NotAuthenticateException("User not Authenticated");
    }
    public Authentication authenticate(String email, String password) {
        Authentication userNamePassword = new UsernamePasswordAuthenticationToken(email, password);
        return this.authenticationManager.authenticate(userNamePassword);
    }
    public Authentication getUserAuthentication() throws NotAuthenticateException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotAuthenticateException("User not Found");
        }
        return authentication;
    }
}

