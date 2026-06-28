package com.company.employee.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.employee.dto.EmployeeRequest;
import com.company.employee.dto.EmployeeResponse;
import com.company.employee.payload.APIResponse;
import com.company.employee.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<APIResponse<EmployeeResponse>> create(
            @Valid @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(

                APIResponse.<EmployeeResponse>builder()

                        .success(true)

                        .message("Employee created successfully")

                        .data(service.save(request))

                        .build()

        );
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<EmployeeResponse>>> getAll(){

        return ResponseEntity.ok(

            APIResponse.<List<EmployeeResponse>>builder()

                    .success(true)

                    .message("Employees fetched successfully")

                    .data(service.findAll())

                    .build()

    );

    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(
            @PathVariable Long id) {

        return service.findById(id);

    }

    @PutMapping("/{id}")
    public EmployeeResponse update(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        return service.update(id, request);

    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        service.delete(id);

    }
}