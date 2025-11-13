package com.reservation.room_reservation_system.dto;

public class DashboardStatsResponse {
    
    private long totalUsers;
    private long pendingRequests;
    private long approvedRequests;
    private long rejectedRequests;
    private long studentCount;
    private long facultyCount;
    private long staffCount;
    private long adminCount;
    
    public DashboardStatsResponse() {}
    
    public DashboardStatsResponse(long totalUsers, long pendingRequests, long approvedRequests, long rejectedRequests,
    long studentCount, long facultyCount, long staffCount, long adminCount) {
        this.totalUsers = totalUsers;
        this.pendingRequests = pendingRequests;
        this.approvedRequests = approvedRequests;
        this.rejectedRequests = rejectedRequests;
        this.studentCount = studentCount;
        this.facultyCount = facultyCount;
        this.staffCount = staffCount;
        this.adminCount = adminCount;
    }
    
    public long getTotalUsers() {
        return totalUsers;
    }
    
    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }
    
    public long getPendingRequests() {
        return pendingRequests;
    }
    
    public void setPendingRequests(long pendingRequests) {
        this.pendingRequests = pendingRequests;
    }
    
    public long getApprovedRequests() {
        return approvedRequests;
    }
    
    public void setApprovedRequests(long approvedRequests) {
        this.approvedRequests = approvedRequests;
    }
    
    public long getRejectedRequests() {
        return rejectedRequests;
    }
    
    public void setRejectedRequests(long rejectedRequests) {
        this.rejectedRequests = rejectedRequests;
    }
    
    public long getStudentCount() {
        return studentCount;
    }
    
    public void setStudentCount(long studentCount) {
        this.studentCount = studentCount;
    }
    
    public long getFacultyCount() {
        return facultyCount;
    }
    
    public void setFacultyCount(long facultyCount) {
        this.facultyCount = facultyCount;
    }
    
    public long getStaffCount() {
        return staffCount;
    }
    
    public void setStaffCount(long staffCount) {
        this.staffCount = staffCount;
    }
    
    public long getAdminCount() {
        return adminCount;
    }
    
    public void setAdminCount(long adminCount) {
        this.adminCount = adminCount;
    }
}
