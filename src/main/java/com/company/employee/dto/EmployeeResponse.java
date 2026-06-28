package com.company.employee.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private Double salary;
}
