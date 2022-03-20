package com.scaler.authsample.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    String createHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    Boolean matchPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
