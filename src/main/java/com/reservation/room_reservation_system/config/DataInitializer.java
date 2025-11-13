package com.reservation.room_reservation_system.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.reservation.roomreservationsystem.repository.UserRepository;
import com.reservation.roomreservationsystem.model.User;
import com.reservation.roomreservationsystem.model.UserRole;



@Configuration
public class DataInitializer implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void run(String... args) throws Exception {

        if (!userRepository.existsByEmail("admin@roomreservation.com")) {

            User admin = new User("admin@roomreservation.com", "admin123", "System Administrator",UserRole.ADMIN);
            userRepository.save(admin);

            logger.info("Default admin user created");
            logger.info("Email: admin@roomreservation.com");
            logger.info("Password: admin123");

        } else {
            logger.info("Admin user already exists");

        }
    }
}