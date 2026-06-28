package com.company.employee.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employee.dto.LoginRequest;
import com.company.employee.dto.LoginResponse;
import com.company.employee.service.AuthService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


private final AuthService service;



@PostMapping("/login")
public LoginResponse login(
@RequestBody LoginRequest request){

    return service.login(request);

}


}