package com.company.employee.service;

import com.company.employee.dto.EmployeeRequest;
import com.company.employee.dto.EmployeeResponse;
import com.company.employee.payload.PageResponse;

public interface EmployeeService {

    EmployeeResponse save(EmployeeRequest request);

    PageResponse<EmployeeResponse> findAll(
        int page,
        int size,
        String sortBy,
        String direction
);


    EmployeeResponse findById(Long id);

    EmployeeResponse update(Long id, EmployeeRequest request);

    void delete(Long id);

}