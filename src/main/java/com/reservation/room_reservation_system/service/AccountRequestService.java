package com.reservation.room_reservation_system.service;
import com.reservation.room_reservation_system.dto.AccountRequestDTO;
import com.reservation.room_reservation_system.dto.AccountRequestResponse;
import com.reservation.room_reservation_system.model.AccountRequest;
import com.reservation.room_reservation_system.model.RequestStatus;
import com.reservation.room_reservation_system.model.User;
import com.reservation.room_reservation_system.repository.AccountRequestRepository;
import com.reservation.room_reservation_system.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountRequestService{
    private final AccountRequestRepository accountRequestRepository;
    private final UserRepository userRepository;
    public AccountRequestService(AccountRequestRepository accountRequestRepository, UserRepository userRepository){
        this.accountRequestRepository = accountRequestRepository;
        this.userRepository = userRepository;

    }

    @Transactional
    public AccountRequestResponse submitRequest(AccountRequestDTO dto){
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email already registered");
        }

        if(accountRequestRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Account request already submitted for this email");

        }

        AccountRequest request = new AccountRequest(dto.getEmail(),dto.getFullName(),
        dto.getRole(),
        dto.getPassword());

        AccountRequest saved = accountRequestRepository.save(request);
        return mapToResponse(saved);
    }

        public List<AccountRequestResponse> getPendingRequests() {
            return  accountRequestRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
            
        }

        @Transactional
        public AccountRequestResponse approveRequest(Long requestId){
            AccountRequest request = accountRequestRepository.findById(requestId).orElseThrow(() -> new IllegalArgumentException("request not found"));

            if (request.getStatus() != RequestStatus.PENDING){
                throw new IllegalArgumentException("Request already reviewed");
            }
            

            User user = new User(
            request.getEmail(),
            request.getPassword(),
            request.getFullName(),
            request.getRole()
            );
            userRepository.save(user);

            request.setStatus(RequestStatus.APPROVED);
            request.setReviewedDate(LocalDateTime.now());
            AccountRequest updated = accountRequestRepository.save(request);
            return mapToResponse(updated);


        }

        @Transactional
        public AccountRequestResponse rejectRequest(Long requestId){
            AccountRequest request = accountRequestRepository.findById(requestId).orElseThrow(() -> new IllegalArgumentException("request not found"));
            if (request.getStatus() != RequestStatus.PENDING){
                throw new IllegalArgumentException("Request has already been reviewed");

            }

            request.setStatus(RequestStatus.REJECTED);
            request.setReviewedDate(LocalDateTime.now());
            AccountRequest updated = accountRequestRepository.save(request);
            return mapToResponse(updated);
        }

        private AccountRequestResponse mapToResponse(AccountRequest request){
            return new AccountRequestResponse(
                request.getId(),
                request.getEmail(),
                request.getFullName(),
                request.getRole(),
                request.getStatus(),
                request.getRequestDate(),
                request.getReviewedDate()
            );

        }



    
}







