package com.reservation.room_reservation_system.service;

import com.reservation.room_reservation_system.dto.DashboardStatsResponse;
import com.reservation.room_reservation_system.dto.UserResponse;
import com.reservation.room_reservation_system.model.RequestStatus;
import com.reservation.room_reservation_system.model.User;
import com.reservation.room_reservation_system.model.UserRole;
import com.reservation.room_reservation_system.repository.AccountRequestRepository;
import com.reservation.room_reservation_system.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    
    private final UserRepository userRepository;
    private final AccountRequestRepository accountRequestRepository;
    
    public AdminService(UserRepository userRepository,AccountRequestRepository accountRequestRepository) {
        this.userRepository = userRepository;
        this.accountRequestRepository = accountRequestRepository;
    }
    
    public DashboardStatsResponse getDashboardStats() {
        long totalUsers = userRepository.count();
        long pendingRequests = accountRequestRepository.countByStatus(RequestStatus.PENDING);
        long approvedRequests = accountRequestRepository.countByStatus(RequestStatus.APPROVED);
        long rejectedRequests = accountRequestRepository.countByStatus(RequestStatus.REJECTED);
        
        long studentCount = userRepository.countByRole(UserRole.STUDENT);
        long facultyCount = userRepository.countByRole(UserRole.FACULTY);
        long staffCount = userRepository.countByRole(UserRole.STAFF);
        long adminCount = userRepository.countByRole(UserRole.ADMIN);
        
        return new DashboardStatsResponse(
            totalUsers,
            pendingRequests,
            approvedRequests,
            rejectedRequests,
            studentCount,
            facultyCount,
            staffCount,
            adminCount
        );
    }
    
    public List<UserResponse> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role)
        .stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
    }
    
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
        .stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
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
