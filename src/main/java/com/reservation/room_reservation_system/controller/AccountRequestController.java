package com.reservation.room_reservation_system.controller;
import com.reservation.room_reservation_system.dto.AccountRequestDTO;
import com.reservation.room_reservation_system.dto.AccountRequestResponse;
import com.reservation.room_reservation_system.service.AccountRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/account-requests")
@CrossOrigin(origins = "*")

public class AccountRequestController {

    private final AccountRequestService accountRequestService;
    public AccountRequestController(AccountRequestService accountRequestService){
        this.accountRequestService = accountRequestService;
    }

    @PostMapping
    public ResponseEntity<AccountRequestResponse> submitRequest(@Valid @RequestBody AccountRequestDTO dto){
        try {
            AccountRequestResponse response = accountRequestService.submitRequest(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
        }

        @GetMapping ("/pending")
        @PreAuthorize("hasAnyRole('ADMIN')")
        public ResponseEntity<List<AccountRequestResponse>> getPendingRequests () {

            List<AccountRequestResponse> requests = accountRequestService.getPendingRequests();
            return ResponseEntity.ok(requests);
        }

        @GetMapping
        public ResponseEntity<List<AccountRequestResponse>> getAllRequests() {

            List<AccountRequestResponse> requests = accountRequestService.getAllRequests();
            return ResponseEntity.ok(requests);
        }
        @PostMapping("/{id}/approve")
        @PreAuthorize("hasAnyRole('ADMIN')")
        public ResponseEntity<AccountRequestResponse> approveRequest (@PathVariable Long id) {
            try {

                AccountRequestResponse response = accountRequestService.approveRequest(id);
                return ResponseEntity.ok(response); 
            } 
            catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        @PostMapping("/{id}/reject")
        @PreAuthorize("hasAnyRole('ADMIN')")
        public ResponseEntity<AccountRequestResponse> rejectRequest(@PathVariable Long id) {
        try {
            AccountRequestResponse response = accountRequestService.rejectRequest(id);
            return ResponseEntity.ok (response) ; 
        } 
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
    

        }
    }

}