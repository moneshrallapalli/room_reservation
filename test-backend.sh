-- Active: 1763025428681@@localhost@5432@roomreservation
#!/bin/bash
#just for testing purpose 
# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

BASE_URL="http://localhost:8080/api"

echo "=========================================="
echo "Room Reservation System - Backend Tests"
echo "=========================================="
echo ""

# Test 1: Check if application is running
echo -e "${YELLOW}Test 1: Health Check${NC}"
HTTP_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/actuator/health)
if [ "$HTTP_STATUS" -eq 200 ]; then
    echo -e "${GREEN}✓ Application is running${NC}"
else
    echo -e "${RED}✗ Application is not running. Start with ./gradlew bootRun${NC}"
    exit 1
fi
echo ""

# Test 2: Admin Login (Default Admin)
echo -e "${YELLOW}Test 2: Admin Login${NC}"
ADMIN_LOGIN=$(curl -s -X POST $BASE_URL/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@roomreservation.com",
    "password": "admin123"
  }')

if echo "$ADMIN_LOGIN" | grep -q "Login successful"; then
    echo -e "${GREEN}✓ Admin login successful${NC}"
    echo "Response: $ADMIN_LOGIN"
else
    echo -e "${RED}✗ Admin login failed${NC}"
    echo "Response: $ADMIN_LOGIN"
fi
echo ""

# Test 3: Submit Account Request - Student
echo -e "${YELLOW}Test 3: Submit Student Account Request${NC}"
STUDENT_REQUEST=$(curl -s -X POST $BASE_URL/account-requests \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@student.edu",
    "fullName": "John Doe",
    "role": "STUDENT",
    "password": "student123"
  }')

if echo "$STUDENT_REQUEST" | grep -q "john.doe@student.edu"; then
    echo -e "${GREEN}✓ Student account request submitted${NC}"
    echo "Response: $STUDENT_REQUEST"
else
    echo -e "${RED}✗ Failed to submit student request${NC}"
    echo "Response: $STUDENT_REQUEST"
fi
echo ""

# Test 4: Submit Account Request - Faculty
echo -e "${YELLOW}Test 4: Submit Faculty Account Request${NC}"
FACULTY_REQUEST=$(curl -s -X POST $BASE_URL/account-requests \
  -H "Content-Type: application/json" \
  -d '{
    "email": "jane.smith@faculty.edu",
    "fullName": "Jane Smith",
    "role": "FACULTY",
    "password": "faculty123"
  }')

if echo "$FACULTY_REQUEST" | grep -q "jane.smith@faculty.edu"; then
    echo -e "${GREEN}✓ Faculty account request submitted${NC}"
    echo "Response: $FACULTY_REQUEST"
else
    echo -e "${RED}✗ Failed to submit faculty request${NC}"
    echo "Response: $FACULTY_REQUEST"
fi
echo ""

# Test 5: Submit Account Request - Staff
echo -e "${YELLOW}Test 5: Submit Staff Account Request${NC}"
STAFF_REQUEST=$(curl -s -X POST $BASE_URL/account-requests \
  -H "Content-Type: application/json" \
  -d '{
    "email": "bob.wilson@staff.edu",
    "fullName": "Bob Wilson",
    "role": "STAFF",
    "password": "staff123"
  }')

if echo "$STAFF_REQUEST" | grep -q "bob.wilson@staff.edu"; then
    echo -e "${GREEN}✓ Staff account request submitted${NC}"
    echo "Response: $STAFF_REQUEST"
else
    echo -e "${RED}✗ Failed to submit staff request${NC}"
    echo "Response: $STAFF_REQUEST"
fi
echo ""

# Test 6: Get Pending Requests
echo -e "${YELLOW}Test 6: Get Pending Account Requests${NC}"
PENDING=$(curl -s $BASE_URL/account-requests/pending)
PENDING_COUNT=$(echo "$PENDING" | grep -o "PENDING" | wc -l)

if [ "$PENDING_COUNT" -ge 3 ]; then
    echo -e "${GREEN}✓ Found $PENDING_COUNT pending requests${NC}"
    echo "Response: $PENDING"
else
    echo -e "${RED}✗ Expected 3+ pending requests, found $PENDING_COUNT${NC}"
    echo "Response: $PENDING"
fi
echo ""

# Test 7: Approve Student Request
echo -e "${YELLOW}Test 7: Approve Student Account Request${NC}"
APPROVE_STUDENT=$(curl -s -X POST $BASE_URL/account-requests/1/approve)

if echo "$APPROVE_STUDENT" | grep -q "APPROVED"; then
    echo -e "${GREEN}✓ Student account approved${NC}"
    echo "Response: $APPROVE_STUDENT"
else
    echo -e "${RED}✗ Failed to approve student account${NC}"
    echo "Response: $APPROVE_STUDENT"
fi
echo ""

# Test 8: Approve Faculty Request
echo -e "${YELLOW}Test 8: Approve Faculty Account Request${NC}"
APPROVE_FACULTY=$(curl -s -X POST $BASE_URL/account-requests/2/approve)

if echo "$APPROVE_FACULTY" | grep -q "APPROVED"; then
    echo -e "${GREEN}✓ Faculty account approved${NC}"
    echo "Response: $APPROVE_FACULTY"
else
    echo -e "${RED}✗ Failed to approve faculty account${NC}"
    echo "Response: $APPROVE_FACULTY"
fi
echo ""

# Test 9: Reject Staff Request
echo -e "${YELLOW}Test 9: Reject Staff Account Request${NC}"
REJECT_STAFF=$(curl -s -X POST $BASE_URL/account-requests/3/reject)

if echo "$REJECT_STAFF" | grep -q "REJECTED"; then
    echo -e "${GREEN}✓ Staff account rejected${NC}"
    echo "Response: $REJECT_STAFF"
else
    echo -e "${RED}✗ Failed to reject staff account${NC}"
    echo "Response: $REJECT_STAFF"
fi
echo ""

# Test 10: Student Login (After Approval)
echo -e "${YELLOW}Test 10: Student Login (After Approval)${NC}"
STUDENT_LOGIN=$(curl -s -X POST $BASE_URL/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@student.edu",
    "password": "student123"
  }')

if echo "$STUDENT_LOGIN" | grep -q "Login successful"; then
    echo -e "${GREEN}✓ Student login successful${NC}"
    echo "Response: $STUDENT_LOGIN"
else
    echo -e "${RED}✗ Student login failed${NC}"
    echo "Response: $STUDENT_LOGIN"
fi
echo ""

# Test 11: Faculty Login
echo -e "${YELLOW}Test 11: Faculty Login (After Approval)${NC}"
FACULTY_LOGIN=$(curl -s -X POST $BASE_URL/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "jane.smith@faculty.edu",
    "password": "faculty123"
  }')

if echo "$FACULTY_LOGIN" | grep -q "Login successful"; then
    echo -e "${GREEN}✓ Faculty login successful${NC}"
    echo "Response: $FACULTY_LOGIN"
else
    echo -e "${RED}✗ Faculty login failed${NC}"
    echo "Response: $FACULTY_LOGIN"
fi
echo ""

# Test 12: Staff Login Should Fail (Rejected)
echo -e "${YELLOW}Test 12: Staff Login Should Fail (Account Rejected)${NC}"
STAFF_LOGIN=$(curl -s -X POST $BASE_URL/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "bob.wilson@staff.edu",
    "password": "staff123"
  }')

if echo "$STAFF_LOGIN" | grep -q "Invalid email or password"; then
    echo -e "${GREEN}✓ Staff login correctly failed (account rejected)${NC}"
    echo "Response: $STAFF_LOGIN"
else
    echo -e "${RED}✗ Staff should not be able to login${NC}"
    echo "Response: $STAFF_LOGIN"
fi
echo ""

# Test 13: Dashboard Statistics
echo -e "${YELLOW}Test 13: Admin Dashboard Statistics${NC}"
STATS=$(curl -s $BASE_URL/admin/stats)

if echo "$STATS" | grep -q "totalUsers"; then
    echo -e "${GREEN}✓ Dashboard statistics retrieved${NC}"
    echo "Response: $STATS"
else
    echo -e "${RED}✗ Failed to get dashboard statistics${NC}"
    echo "Response: $STATS"
fi
echo ""

# Test 14: Get All Users
echo -e "${YELLOW}Test 14: Get All Users${NC}"
ALL_USERS=$(curl -s $BASE_URL/users)
USER_COUNT=$(echo "$ALL_USERS" | grep -o '"id"' | wc -l)

if [ "$USER_COUNT" -ge 3 ]; then
    echo -e "${GREEN}✓ Found $USER_COUNT users (Admin + 2 approved)${NC}"
    echo "Response: $ALL_USERS"
else
    echo -e "${RED}✗ Expected 3+ users, found $USER_COUNT${NC}"
    echo "Response: $ALL_USERS"
fi
echo ""

# Test 15: Get Users by Role - STUDENT
echo -e "${YELLOW}Test 15: Get Students${NC}"
STUDENTS=$(curl -s $BASE_URL/admin/users/role/STUDENT)

if echo "$STUDENTS" | grep -q "STUDENT"; then
    echo -e "${GREEN}✓ Successfully retrieved students${NC}"
    echo "Response: $STUDENTS"
else
    echo -e "${RED}✗ Failed to get students${NC}"
    echo "Response: $STUDENTS"
fi
echo ""

# Test 16: Invalid Login Attempt
echo -e "${YELLOW}Test 16: Invalid Login Attempt (Wrong Password)${NC}"
INVALID_LOGIN=$(curl -s -X POST $BASE_URL/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@student.edu",
    "password": "wrongpassword"
  }')

if echo "$INVALID_LOGIN" | grep -q "Invalid email or password"; then
    echo -e "${GREEN}✓ Invalid login correctly rejected${NC}"
    echo "Response: $INVALID_LOGIN"
else
    echo -e "${RED}✗ Invalid login should have failed${NC}"
    echo "Response: $INVALID_LOGIN"
fi
echo ""

# Test 17: Duplicate Email Request
echo -e "${YELLOW}Test 17: Duplicate Email Request (Should Fail)${NC}"
DUPLICATE=$(curl -s -X POST $BASE_URL/account-requests \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@student.edu",
    "fullName": "John Duplicate",
    "role": "STUDENT",
    "password": "password"
  }')

if echo "$DUPLICATE" | grep -q "already"; then
    echo -e "${GREEN}✓ Duplicate email correctly rejected${NC}"
    echo "Response: $DUPLICATE"
else
    echo -e "${RED}✗ Duplicate email should have been rejected${NC}"
    echo "Response: $DUPLICATE"
fi
echo ""

echo "=========================================="
echo -e "${GREEN}All Tests Completed!${NC}"
echo "=========================================="
