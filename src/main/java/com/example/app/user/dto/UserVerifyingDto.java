package com.example.app.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVerifyingDto {
    private String email;
    private String token;
    private Integer code;
}
