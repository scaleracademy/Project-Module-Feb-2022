package com.scaler.authsample.dtos.requests;

import lombok.Getter;

@Getter
public class UserLoginRequestBody {
    String username;
    String password;
}
