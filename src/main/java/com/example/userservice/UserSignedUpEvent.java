package com.example.userservice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignedUpEvent {
    private Long userId;
    private String email;
    private String name;
}
