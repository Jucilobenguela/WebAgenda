package com.benguela.WebAgenda_API.security;

import com.benguela.WebAgenda_API.infra.exception.NotAuthenticateException;
import com.benguela.WebAgenda_API.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    Authentication authentication;
  public User getAuthenticatedUser() throws NotAuthenticateException {
      authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null || !authentication.isAuthenticated() || !(authentication instanceof UsernamePasswordAuthenticationToken)) {
          throw new NotAuthenticateException("User not authenticated");
      }
      return (User) authentication.getPrincipal();
  }
}
