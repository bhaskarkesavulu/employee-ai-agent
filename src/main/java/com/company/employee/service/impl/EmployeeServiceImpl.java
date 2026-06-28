package com.company.employee.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log
            = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository repository;

    @Override
    public EmployeeResponse save(EmployeeRequest request) {

        log.info(
                "Creating employee with email {}",
                request.getEmail()
        );
        Employee employee = EmployeeMapper.toEntity(request);

        Employee savedEmployee = repository.save(employee);

        log.info(
                "Employee created successfully id {}",
                savedEmployee.getId());
        return EmployeeMapper.toResponse(savedEmployee);
    }

    @Override
    public Page<EmployeeResponse> findAll(
            int page,
            int size,
            String sortBy,
            String direction
    ) {

        Sort sort
                = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable
                = PageRequest.of(
                        page,
                        size,
                        sort
                );

        return repository.findAll(pageable)
                .map(EmployeeMapper::toResponse);

    }

    @Override
    public EmployeeResponse findById(Long id) {
        log.info(
                "Fetching employee id {}",
                id
        );

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
