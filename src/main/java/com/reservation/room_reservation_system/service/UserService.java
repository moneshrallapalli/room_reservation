package com.reservation.room_reservation_system.service;

import com.reservation.room_reservation_system.dto.LoginRequest;
import com.reservation.room_reservation_system.dto.LoginResponse;
import com.reservation.room_reservation_system.dto.UserResponse;
import com.reservation.room_reservation_system.model.User;
import com.reservation.room_reservation_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        
        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        
        return new LoginResponse(
            user.getId(),
            user.getEmail(),
            user.getFullName(),
            user.getRole(),
            "Login successful"
        );
        
        
    }
    
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return mapToResponse(user);
    }
    
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return mapToResponse(user);
    }
    
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    private UserResponse mapToResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getEmail(),
            user.getFullName(),
            user.getRole(),
            user.isApproved(),
            user.getCreatedAt()
        );
    }
}
