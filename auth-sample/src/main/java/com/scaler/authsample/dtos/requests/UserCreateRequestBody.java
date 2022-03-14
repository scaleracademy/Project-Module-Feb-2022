package com.scaler.authsample.dtos.requests;

import lombok.Getter;

@Getter
public class UserCreateRequestBody {
    String username;
    String password;
    String email;
}
