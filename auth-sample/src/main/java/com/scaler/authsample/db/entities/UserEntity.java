package com.scaler.authsample.db.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(unique = true)
    String email;

    @Column(nullable = false)
    String password;
}
