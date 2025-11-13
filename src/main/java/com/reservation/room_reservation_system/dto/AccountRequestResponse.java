package com.reservation.room_reservation_system.dto;
import com.reservation.room_reservation_system.model.RequestStatus;
import com.reservation.room_reservation_system.model.UserRole;
import java.time.LocalDateTime;




public class AccountRequestResponse {

    private Long id;
    private String email;
    private String fullName;
    private UserRole role;
    private RequestStatus status;
    private LocalDateTime requestDate;
    private LocalDateTime reviewedDate;

    public AccountRequestResponse(){}
    public AccountRequestResponse(Long id, String email, String fullname, UserRole role, RequestStatus status, LocalDateTime requestDate, LocalDateTime reviewedDate){
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.status = status;
        this.requestDate = requestDate;
        this.reviewedDate = reviewedDate;

    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;

    }
    public String getEmail(){
        return email;
    
    }
    public void setEmail(String email){
        this.email = email;

    }
    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public UserRole getRole(){
        return role;

    }
    public void setUserRole(UserRole role){
        this.role  = role;

    }
    public RequestStatus getStatus(){
        return status;
    }
    public void setStatus(RequestStatus status){
        this.status = status;

    }

    public LocalDateTime getRequestDate(){
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate){
        this.requestDate = requestDate;

    }
    public LocalDateTime getReviewedDate(){
        return reviewedDate;

    }
    public void setReviewedDate(LocalDateTime reviewedDate){
        this.reviewedDate = reviewedDate;

    }
}
