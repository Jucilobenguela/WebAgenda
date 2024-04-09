package com.benguela.webAgendaAPI.service.interfac;


import com.benguela.webAgendaAPI.exception.InvalidEmailException;
import com.benguela.webAgendaAPI.exception.InvalidPasswordException;
import com.benguela.webAgendaAPI.exception.NotFindEmailException;
import com.benguela.webAgendaAPI.model.User;

public interface UserServiceI  {
     void isIdentityPassword(String password, String passwordRepeated) throws InvalidPasswordException;

     User validateEmail(String email) throws NotFindEmailException;


     User save(User user);

     User validateUserRegister(User user) throws NotFindEmailException, InvalidPasswordException, InvalidEmailException;
     public String encoderPassword(String password);
}
