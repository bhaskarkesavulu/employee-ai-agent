package com.company.employee.service;

import org.springframework.data.domain.Page;

import com.company.employee.dto.EmployeeRequest;
import com.company.employee.dto.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse save(EmployeeRequest request);

    Page<EmployeeResponse> findAll(
            int page,
            int size,
            String sortBy,
            String direction
    );


    EmployeeResponse findById(Long id);

    EmployeeResponse update(Long id, EmployeeRequest request);

    void delete(Long id);

}