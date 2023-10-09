package com.demo.springbootemployeeapi.controller;

import com.demo.springbootemployeeapi.model.Employee;
import com.demo.springbootemployeeapi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {


    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //display list of employees
    @GetMapping("/")
    public List<Employee> viewHomePage(){
        return findPaginated(1);
    }

    @GetMapping("/getOneEmployee/{id}")
    public Employee getOneEmployee(@PathVariable(value = "id") long id){
        return employeeService.getEmployeeById(id);

    }

    @PostMapping("/saveEmployee")
    public void saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    @PutMapping("/updateEmployee")
    public void updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable(value = "id") long id){
        employeeService.deleteEmployeeById(id);
    }

    @GetMapping("/page/{pageNo}")
    public List<Employee> findPaginated(@PathVariable(value = "pageNo") int pageNo){
        int pageSize = 10;
        Page<Employee> page = employeeService.findPaginated(pageNo,pageSize);
        List<Employee> listEmployees = page.getContent();
        return listEmployees;
    }

}
