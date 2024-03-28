package com.benguela.WebAgenda_API.service;

import com.benguela.WebAgenda_API.infra.exception.InvalidEmailException;
import com.benguela.WebAgenda_API.infra.exception.InvalidPasswordException;
import com.benguela.WebAgenda_API.infra.exception.NotFindEmailException;
import com.benguela.WebAgenda_API.model.User;
import org.springframework.security.core.Authentication;

public interface UserServiceI {
     void isIdentityPassword(String password, String passwordRepeated) throws InvalidPasswordException;

     User validateEmail(String email) throws NotFindEmailException;


     User save(User user);

     User validateUserRegister(User user) throws NotFindEmailException, InvalidPasswordException, InvalidEmailException;
     public String encoderPassword(String password);
}
