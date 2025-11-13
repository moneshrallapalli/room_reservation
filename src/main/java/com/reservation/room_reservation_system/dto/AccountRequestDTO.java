
package com.reservation.room_reservation_system.dto;

import com.reservation.room_reservation_system.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccountRequestDTO {

    @NotBlank(message = "email is mandatory")
    @Email(message = "valid email is required")
    private String email;

    @NotBlank(message = "full name is required")
    private String fullname;

    @NotNull(message = "role is required")
    private UserRole role;

    @NotBlank(message = "password is required")
    private String password;

    public AccountRequestDTO(){}

    public AccountRequestDTO(String email, String fullname, UserRole role, String password){
        this.email = email;
        this.fullname = fullname;
        this.role = role;
        this.password = password;



    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;

    }
    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role){
        this.role = role;
    }

    public String getPassword() {
        return password;

    }
    public void setPassword(String password){
        this.password = password;

    }


    
}