package com.company.employee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.employee.dto.EmployeeRequest;
import com.company.employee.dto.EmployeeResponse;
import com.company.employee.entity.Employee;
import com.company.employee.exception.EmployeeNotFoundException;
import com.company.employee.mapper.EmployeeMapper;
import com.company.employee.repository.EmployeeRepository;
import com.company.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Override
    public EmployeeResponse save(EmployeeRequest request) {

        Employee employee = EmployeeMapper.toEntity(request);

        Employee savedEmployee = repository.save(employee);

        return EmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

    }

    @Override
    public EmployeeResponse findById(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee with ID " + id + " not found"));

        return EmployeeMapper.toResponse(employee);

    }

    @Override
    public EmployeeResponse update(Long id,
            EmployeeRequest request) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee with ID " + id + " not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());

        Employee updatedEmployee = repository.save(employee);

        return EmployeeMapper.toResponse(updatedEmployee);

    }

    @Override
    public void delete(Long id) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee with ID " + id + " not found"));

        repository.delete(employee);

    }

}