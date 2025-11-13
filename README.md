Room Reservation System - Project Documentation
Project Overview
Project Name: Room Reservation SystemTeam: Team 4 Date: November 6, 2025

1. Project Description
The Room Reservation System is a web-based application that enables university users (students, faculty, and staff) to search, book, and manage room reservations across multiple campus buildings. The system provides an interactive, map-based interface for room selection and includes comprehensive administrative controls.

2. Functional Requirements
2.1 User Roles and Privileges
The system supports four distinct user roles:
1. Admin
    * Full system access
    * User account approval
    * Room and building management
    * System configuration
    * Reservation oversight and cancellation authority
2. Faculty
    * Unrestricted booking privileges
    * No time slot limits
    * Can Have Access to faculty-only rooms
3. Students
    * Limited booking privileges
    * Time slot restrictions apply
    * Cannot access faculty-only rooms
    * Can request extra time (requires admin approval)
4. Staff
    * University employees (non-faculty)
    * Medium-level privileges
    * Specific restrictions to be defined
2.2 Room Search and Filtering
Users can search for available rooms based on:
* Date and time range
* Building location
* Room capacity (number of seats)
* Amenities:
    * Monitor availability
    * Whiteboard availability
    * Elevator access (for non-ground-floor rooms)
    * Other configurable features
Search Results:
* Display available rooms with pagination
* Show real-time availability status
* Present results in an interactive visual format
2.3 Booking Management
Reservation Process:
* Users specify start and end times for bookings
* System enforces availability windows (e.g., 8 AM - 11 PM)
* Immediate confirmation upon successful booking
* Role-based restrictions automatically applied
Time Slot Management:
* Time slot selection (fixed intervals)
* User-defined duration within allowed limits
* Admin-configurable default availability windows
* Extended time requests subject to admin approval
User Actions:
* View all personal reservations
* Cancel existing reservations
* Modify bookings (subject to availability)
2.4 Administrative Functions
Room and Building Management:
* Add new buildings and rooms to the system
* Update room information and amenities
* Configure room-specific rules and restrictions
* Set faculty-only room designations
User Management:
* Review and approve user registration requests
* Verify user roles (student/faculty/staff)
* Activate or deactivate user accounts
* Monitor user activity
System Configuration:
* Define booking time slot limits per role
* Set building operating hours
* Configure notification preferences
* Modify system-wide settings through dedicated settings interface
Override Capabilities:
* Cancel any reservation with justification
* Approve extra time requests from users
* Handle emergency room unavailability
2.5 Authentication and Authorization
User Registration:
* Self-registration with role declaration
* Pending status until admin approval
* Email verification (implied)
Sign-In Process:
* Standard authentication for students, faculty, and staff
* Two-factor authentication (SMS-based/DUO(anything!!!) required for admin access
2.6 Notification System
The system automatically sends notifications for:
1. Booking Confirmation - Immediate confirmation upon successful reservation
2. Upcoming Reservation Reminders - Alerts as reservation date approaches
3. Cancellation Notices - Notifications when reservations are cancelled (by user or admin)
4. Approval Status - Updates on extra time requests or account activation

3. Non-Functional Requirements
3.1 Performance Metrics
The system must meet the following performance standards:
Metric	Requirement
Concurrent Active Sessions	1,000,000 users
Search Query Response Time	< 2 seconds
Transaction Response Time	< 1 second
Page Load Time	< 3 seconds
Database Query Execution	< 500 milliseconds
3.2 Scalability
* Automatic Scaling: System must auto-scale from 1 million to 5 million concurrent users
* No Manual Intervention: Scaling must occur automatically without manual configuration
* Performance Consistency: All performance metrics must be maintained during scaling
3.3 Testing Requirements
* Comprehensive performance testing required
* Load testing to verify concurrent user capacity
* Stress testing to validate auto-scaling functionality
* Response time verification tests
* Documentation of test results proving compliance

4. Technical Architecture
4.1 Frontend
Technology: Angular
UI/UX Requirements:
* Significant improvement over previous projects
* Focus on user accessibility and intuitive design
* Interactive visual elements
* Responsive design for multiple devices
Interactive Building Maps:
* Visual floor plans for each building
* Top-down view of room layouts
* Click-to-select room functionality
* Color-coded availability status
* Floor-by-floor navigation
* Real-time availability updates
Initial Scope:
* Begin with one building 
* Design must support multiple buildings
* Scalable architecture for adding new buildings
4.2 Backend
Architecture: Microservices
Required Services:
* Minimum 3 Business Services (functional separation of concerns)
* Edge Service (API Gateway)
* Config Service (centralized configuration management)
Service Deployment:
* All services run on Kubernetes
* Container orchestration for auto-scaling
* Service mesh for inter-service communication
4.3 Database
Prohibited: SQLite
Approved Options:
* PostgreSQL (SQL option)
* Any NoSQL database (MongoDB, Cassandra, etc.)
Key Data Entities:
* Buildings and room information
* User profiles and roles
* Reservations and booking history
* System configuration and rules
* Notification logs

5. User Interface Specifications
5.1 Room Selection Interface
Building Selection:
1. User selects building from available options
2. System loads interactive floor plan
3. Floor selector allows navigation between levels
4. Rooms displayed with availability indicators
Room Booking Flow:
1. Visual room selection via map (not dropdown menus)
2. Room details displayed on selection (capacity, amenities)
3. Date and time picker for reservation
4. Instant availability validation
5. Confirmation screen with booking summary
5.2 Admin Interface
Settings Dashboard:
* Centralized control panel for all configurations
* User approval queue
* System metrics and monitoring
* Building and room management tools
* Notification template management

6. Design Considerations
6.1 Database-Driven Configuration
All business rules must be stored in the database:
* Room availability windows
* Role-based time slot limits
* Building operating hours
* Room-specific restrictions
* Amenity definitions
6.2 Excluded Features
AI Integration:
* No AI-based features planned
* Auto-generated building maps rejected (accuracy concerns)
6.3 Initial Development Scope
Starting Point:
* Single building implementation
* Core booking functionality
* Basic admin controls
* Essential notification system
Expansion Capability:
* Architecture supports multiple buildings
* Scalable to campus-wide deployment
* Modular design for feature additions

7. Success Criteria
1. Performance Testing: Documented proof of handling 1M concurrent users
2. Response Times: All transactions meet sub-second requirements
3. UI/UX Quality: Significant improvement in interface design and user experience
4. Scalability: Demonstrated auto-scaling to 5M users
5. Functional Completeness: All user roles and booking workflows operational
6. Admin Controls: Full configuration management through UI
7. Notification System: All automated communications functioning correctly

8. Next Steps
1. Finalize microservices architecture design
2. Design database schema
3. Create interactive building map mockups
4. Develop authentication system with 2FA for admin
5. Implement core booking engine
6. Set up Kubernetes cluster for deployment
7. Establish performance testing framework

Document Status: Initial RequirementsLast Updated: November 6, 2025Team Members: Team 4
