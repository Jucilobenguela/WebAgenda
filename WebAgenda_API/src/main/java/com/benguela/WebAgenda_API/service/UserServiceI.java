package com.benguela.WebAgenda_API.service;

import com.benguela.WebAgenda_API.infra.exception.InvalidEmailException;
import com.benguela.WebAgenda_API.infra.exception.InvalidPasswordException;
import com.benguela.WebAgenda_API.infra.exception.NotFindEmailException;
import com.benguela.WebAgenda_API.model.User;

public interface UserServiceI {
     void isIdentityPassword(String password, String passwordRepeated) throws InvalidPasswordException;

     void validateUserDetails(User user);

     User authenticated(User user);

     User save(User user);

     User validateUserRegister(User user) throws NotFindEmailException, InvalidPasswordException, InvalidEmailException;
     public String encoderPassword(String password);
}
