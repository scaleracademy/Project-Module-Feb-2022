package com.scaler.authsample.services;

import com.scaler.authsample.db.entities.UserEntity;
import com.scaler.authsample.db.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordService passSvc;

    public UserService(UserRepository userRepo, PasswordService passSvc) {
        this.userRepo = userRepo;
        this.passSvc = passSvc;
    }

    public UserEntity createUser(String username, String email, String password) {
        UserEntity newUser = UserEntity.builder()
                .email(email)
                .username(username)
                .password(passSvc.createHash(password))
                .build();
        return userRepo.save(newUser);
    }

    public UserEntity verifyUser(String username, String password) {
        UserEntity savedUser = userRepo.findByUsername(username);

        if (savedUser == null) {
            throw new UserNotFoundException(username);
        }

        if (!passSvc.matchPassword(password, savedUser.getPassword())) {
            throw new UserPasswordMismatchException();
        }

        return savedUser;
    }

    public UserEntity getUserByUsername(String username) {
        UserEntity savedUser = userRepo.findByUsername(username);

        if (savedUser == null) {
            throw new UserNotFoundException(username);
        }

        // make a copy so as to not pass the password above this layer

        return UserEntity.builder()
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .id(savedUser.getId())
                .build();
    }


    static class UserNotFoundException extends IllegalStateException {

        public UserNotFoundException(String username) {
            super("User with username = " + username + " not found");
        }
    }

    static class UserPasswordMismatchException extends IllegalArgumentException {

        public UserPasswordMismatchException() {
            super("Wrong password given for this user");
        }
    }
}
