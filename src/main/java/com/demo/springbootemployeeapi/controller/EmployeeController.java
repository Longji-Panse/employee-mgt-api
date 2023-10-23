package com.demo.springbootemployeeapi.controller;

import com.demo.springbootemployeeapi.model.Employee;
import com.demo.springbootemployeeapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController

public class EmployeeController {


    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    //display list of employees
    @GetMapping("/employee")
    public List<Employee> viewHomePage(){
        Page<Employee> employeePage = employeeService.getAllEmployees(1, 10);
        if (employeePage != null) {
            return employeePage.getContent();
        } else {
            // Handle the case when employeePage is null
            return Collections.emptyList(); // Or return an appropriate response
        }

       // List<Employee> employeeList = employeeService.getAllEmployees(1, 10).getContent();
      //  return employeeList;
    }
    @PostMapping("/employee")
    public void saveEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.saveEmployee(employee);
    }
    @GetMapping("/employee/{id}")
    public Employee getOneEmployee(@PathVariable(value = "id") long id){
        return employeeService.getEmployeeById(id);
    }
    @PutMapping("/employee")
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable(value = "id") long id){
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/employee/page/{pageNo}")
    public Page<Employee> findPaginated(@PathVariable(value = "pageNo") int pageNo){
        int pageSize = 10;
        Page<Employee> page = employeeService.findAllPaginated(pageNo,pageSize);
        return page;
    }

}
