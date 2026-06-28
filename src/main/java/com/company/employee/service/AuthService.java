package com.company.employee.service;


import com.company.employee.dto.LoginRequest;
import com.company.employee.dto.LoginResponse;


public interface AuthService {


    LoginResponse login(LoginRequest request);


}