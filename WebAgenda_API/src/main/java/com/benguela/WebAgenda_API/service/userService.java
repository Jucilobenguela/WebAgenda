package com.benguela.WebAgenda_API.service;

import com.benguela.WebAgenda_API.infra.exception.InvalidEmailException;
import com.benguela.WebAgenda_API.infra.exception.InvalidPasswordException;
import com.benguela.WebAgenda_API.infra.exception.NotFindEmailException;
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
    public void isIdentityPassword(String password, String passwordRepeated) throws InvalidPasswordException {
        if(!password.equals(passwordRepeated)){
            throw new InvalidPasswordException("Password do not know");
        }
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
    public User validateUserRegister(User userRequest) throws NotFindEmailException, InvalidPasswordException, InvalidEmailException {
        if (isValidEmail(userRequest.getEmail()) && isValidPassword(userRequest.getPassword())) {
            UserDetails userDataBase =  userRepository.findByEmail(userRequest.getEmail());
            if (userDataBase != null) {
                throw new NotFindEmailException("Email already exists");
            } else {
                this.user = userRequest;
                this.user.setPassword(encoderPassword(userRequest.getPassword()));
            }
        }
        return save(this.user);
    }
    private boolean isValidEmail(String email) throws InvalidEmailException {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(regex)){
            throw new InvalidEmailException("Invalid Email");
        }
        return true;
    }
    private boolean isValidPassword(String password) throws InvalidPasswordException {
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}";
        if (!password.matches(regex)){
            throw new InvalidPasswordException("Invalid Password!");
        }
        return true;
    }

    public String encoderPassword(String password) throws NullPointerException {
        if(password.isEmpty()){
            throw new NullPointerException();
        }
        return passwordEncoder.encode(password);
    }
}
