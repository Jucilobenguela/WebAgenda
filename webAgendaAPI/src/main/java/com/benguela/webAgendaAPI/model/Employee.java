package com.benguela.webAgendaAPI.model;

import com.benguela.webAgendaAPI.util.enums.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String employeeName;
    @NotBlank
    private String password;
    private RoleEnum role;

    public Employee(String employeeName, String password) {
        this.employeeName = employeeName;
        this.password = password;
    }
    public Employee(){

    }

    public Long getId() {
        return id;
    }

    @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            if(role == RoleEnum.ADMIN){
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER"));
            }
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.employeeName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
