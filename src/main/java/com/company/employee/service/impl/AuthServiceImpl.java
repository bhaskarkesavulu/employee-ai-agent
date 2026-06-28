package com.company.employee.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.employee.dto.LoginRequest;
import com.company.employee.dto.LoginResponse;
import com.company.employee.repository.UserRepository;
import com.company.employee.security.JwtService;
import com.company.employee.service.AuthService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl 
implements AuthService {


private final UserRepository repository;

private final JwtService jwtService;

private final BCryptPasswordEncoder encoder;



@Override
public LoginResponse login(
        LoginRequest request){


var user =
repository.findByUsername(
        request.getUsername()
)
.orElseThrow(
        () -> new RuntimeException(
        "User not found")
);



if(!encoder.matches(
        request.getPassword(),
        user.getPassword())){


    throw new RuntimeException(
    "Invalid password");

}



String token =
jwtService.generateToken(
        user.getUsername()
);



return LoginResponse.builder()

.token(token)

.build();


}


}