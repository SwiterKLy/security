package com.switerkly.labs.auth;

import lombok.*;

@Builder
@Getter
@Setter
public class AuthenticationResponse {
    private String token;
}
