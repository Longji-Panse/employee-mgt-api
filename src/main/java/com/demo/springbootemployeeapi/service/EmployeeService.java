package com.demo.springbootemployeeapi.service;

import com.demo.springbootemployeeapi.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Page<Employee> getAllEmployees(int pageNo, int pageSize);
    Employee saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);

    Page<Employee> findAllPaginated(int pageNo, int pageSize);

    }
