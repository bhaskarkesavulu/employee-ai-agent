package com.company.employee.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.employee.dto.EmployeeRequest;
import com.company.employee.dto.EmployeeResponse;
import com.company.employee.payload.APIResponse;
import com.company.employee.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @Operation(
            summary = "Create employee",
            description = "Creates a new employee in the system"
    )
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
    public ResponseEntity<APIResponse<Page<EmployeeResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Page<EmployeeResponse> employees
                = service.findAll(
                        page,
                        size,
                        sortBy,
                        direction
                );

        return ResponseEntity.ok(
                APIResponse.<Page<EmployeeResponse>>builder()
                        .success(true)
                        .message("Employees fetched successfully")
                        .data(employees)
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
