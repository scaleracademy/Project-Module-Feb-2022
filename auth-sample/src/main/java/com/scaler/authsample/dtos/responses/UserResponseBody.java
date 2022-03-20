package com.scaler.authsample.dtos.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseBody {
    Long id;
    String username;
    String email;
}
