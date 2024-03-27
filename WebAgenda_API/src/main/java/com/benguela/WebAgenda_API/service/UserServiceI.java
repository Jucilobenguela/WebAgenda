package com.benguela.WebAgenda_API.service;

import com.benguela.WebAgenda_API.infra.exception.NotFindEmail;
import com.benguela.WebAgenda_API.model.User;

public interface UserServiceI {
     boolean isIdentityPassword(String password, String passwordRepeated);

     void validateUserDetails(User user);

     User authenticated(User user);

     User save(User user);

     User validateUserRegister(User user) throws NotFindEmail;
     public String encoderPassword(String password);
}
