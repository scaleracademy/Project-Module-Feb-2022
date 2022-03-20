package com.scaler.authsample.controllers;

import com.scaler.authsample.db.entities.UserEntity;
import com.scaler.authsample.dtos.requests.UserCreateRequestBody;
import com.scaler.authsample.dtos.requests.UserLoginRequestBody;
import com.scaler.authsample.dtos.responses.UserResponseBody;
import com.scaler.authsample.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
    private UserService userSvc;

    public UsersController(UserService userSvc) {
        this.userSvc = userSvc;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseBody> createUser(
            @RequestBody UserCreateRequestBody body
    ) {
        UserEntity createdUser = userSvc.createUser(
                body.getUsername(),
                body.getEmail(),
                body.getPassword()
        );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header("Token", "xxxxx")
                .body(
                        UserResponseBody.builder()
                                .email(createdUser.getEmail())
                                .id(createdUser.getId())
                                .username(createdUser.getUsername())
                                .build()
                );
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponseBody> loginUser(
            @RequestBody UserLoginRequestBody body
    ) {
        UserEntity existingUser = userSvc.verifyUser(
                body.getUsername(),
                body.getPassword()
        );

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header("Token", "xxxxx")
                .body(
                        UserResponseBody.builder()
                                .email(existingUser.getEmail())
                                .id(existingUser.getId())
                                .username(existingUser.getUsername())
                                .build()
                );
    }


}
