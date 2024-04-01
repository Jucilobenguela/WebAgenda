package com.benguela.webAgendaAPI.service;


import com.benguela.webAgendaAPI.exception.InvalidEmailException;
import com.benguela.webAgendaAPI.exception.InvalidPasswordException;
import com.benguela.webAgendaAPI.exception.NotFindEmailException;
import com.benguela.webAgendaAPI.model.User;
import com.benguela.webAgendaAPI.repository.UserRepository;
import com.benguela.webAgendaAPI.segurity.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceI {
    User user;
    @Autowired
    AuthService authService;
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
    public User validateEmail(String email) throws NotFindEmailException {
        User user1 = (User)userRepository.findByEmail(email);
        if (user1==null){
            throw new NotFindEmailException("Email don´t existed");
        }
        return user1;
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
            throw new NullPointerException("Password Empty");
        }
        return passwordEncoder.encode(password);
    }
}
