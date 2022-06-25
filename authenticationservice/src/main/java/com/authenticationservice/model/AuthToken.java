package com.authenticationservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthToken implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
}
