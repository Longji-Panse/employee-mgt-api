package com.demo.springbootemployeeapi.repository;

import com.demo.springbootemployeeapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
