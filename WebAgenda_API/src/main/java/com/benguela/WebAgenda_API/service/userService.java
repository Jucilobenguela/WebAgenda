package com.benguela.WebAgenda_API.service;

import com.benguela.WebAgenda_API.infra.exception.NotFindEmail;
import com.benguela.WebAgenda_API.model.User;
import com.benguela.WebAgenda_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userService implements UserServiceI{
    User user;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public boolean isIdentityPassword(String password, String passwordRepeated) {
        return password.equals(passwordRepeated);
    }

    @Override
    public void validateUserDetails(User user) {

    }

    @Override
    public User authenticated(User user) {
        return null;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User validateUserRegister(User user) throws NotFindEmail {
        if (isValidEmail(user.getEmail()) && isValidPassword(user.getPassword())) {
            UserDetails userDataBase =  userRepository.findByEmail(user.getEmail());
            if (userDataBase != null) {
                throw new NotFindEmail("Email already exists");
            } else {
                this.user = user;
                this.user.setPassword(encoderPassword(user.getPassword()));
            }
        }
        return save(this.user);
    }

    private boolean isValidPassword(String password) {
        return true;
    }

    private boolean isValidEmail(String email) {
        return true;
    }

    public String encoderPassword(String password) throws NullPointerException {
        if(password.isEmpty()){
            throw new NullPointerException();
        }
        return passwordEncoder.encode(password);
    }
}
