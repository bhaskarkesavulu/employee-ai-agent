package com.company.employee.service;

import java.util.List;

import com.company.employee.dto.EmployeeRequest;
import com.company.employee.dto.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse save(EmployeeRequest request);

    List<EmployeeResponse> findAll();

    EmployeeResponse findById(Long id);

    EmployeeResponse update(Long id, EmployeeRequest request);

    void delete(Long id);

}