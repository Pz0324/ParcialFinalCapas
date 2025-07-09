package com.uca.parcialfinalncapas.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {


    private String token;

    public LoginResponse(String token) {
    }
}
