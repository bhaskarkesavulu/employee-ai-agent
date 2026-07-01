package com.company.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.company.employee.dto.EmployeeRequest;
import com.company.employee.dto.EmployeeResponse;
import com.company.employee.entity.Employee;
import com.company.employee.repository.EmployeeRepository;
import com.company.employee.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    @Test
    void testSample() {
        System.out.println("JUnit is working!");
    }

    @Test
    void shouldSaveEmployee() {

        // Arrange
        EmployeeRequest request = new EmployeeRequest();
        request.setFirstName("Bhaskar");
        request.setLastName("K");
        request.setEmail("bhaskar@gmail.com");
        request.setDepartment("IT");
        request.setSalary(50000.0);

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Bhaskar");
        employee.setLastName("K");
        employee.setEmail("bhaskar@gmail.com");
        employee.setDepartment("IT");
        employee.setSalary(50000.0);

        when(repository.save(any(Employee.class))).thenReturn(employee);

        // Act
        EmployeeResponse response = service.save(request);

        // Assert
        assertNotNull(response);
        assertEquals("Bhaskar", response.getFirstName());
        assertEquals("IT", response.getDepartment());
        assertEquals("bhaskar@gmail.com", response.getEmail());

        verify(repository, times(1)).save(any(Employee.class));
    }

}