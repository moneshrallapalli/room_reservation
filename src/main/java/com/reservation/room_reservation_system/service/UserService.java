package com.reservation.room_reservation_system.service;

import com.reservation.room_reservation_system.dto.LoginRequest;
import com.reservation.room_reservation_system.dto.LoginResponse;
import com.reservation.room_reservation_system.dto.UserResponse;
import com.reservation.room_reservation_system.model.User;
import com.reservation.room_reservation_system.repository.UserRepository;
import com.reservation.room_reservation_system.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());
        
        return new LoginResponse(
            user.getId(),
            user.getEmail(),
            user.getFullName(),
            user.getRole(),
            "Login successful",
            token
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
