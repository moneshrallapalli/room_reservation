package com.reservation.room_reservation_system.dto;

import java.time.LocalDateTime;

import com.reservation.room_reservation_system.model.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private String fullName;
    private UserRole role;
    private boolean approved;
    private LocalDateTime createdAt;

    
}
