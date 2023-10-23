package com.demo.springbootemployeeapi.controller;

import com.demo.springbootemployeeapi.model.Employee;
import com.demo.springbootemployeeapi.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = EmployeeController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    public void  init(){
       employee = Employee.builder().firstName("john").lastName("cena").email("jc@wwe.com").build();
    }

    @Test
    public void EmployeeController_CreateEmployee_ReturnCreatedEmployee() throws Exception{
        given(employeeService.saveEmployee(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void EmployeeController_GetEmployeeById_ReturnEmployee() throws Exception{
        long employeeId = 1;
        when(employeeService.getEmployeeById(employeeId)).thenReturn(employee);

        ResultActions response = mockMvc.perform(get("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void EmployeeController_UpdateEmployee_ReturnUpdatedEmployee() throws Exception{
        given(employeeService.saveEmployee(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(put("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void EmployeeController_DeleteEmployee_ReturnString() throws Exception{
       long employeeId = 1;
       doNothing().when(employeeService).deleteEmployeeById(employeeId);

        ResultActions response = mockMvc.perform(delete("/employee/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void EmployeeController_GetAllEmployees_ReturnPage() throws Exception{
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1L, "John", "Doe", "john@example.com"));
        employeeList.add(new Employee(2L, "Jane", "Smith", "jane@example.com"));

        // Create a Page<Employee> from the list (you might want to use a pageable)
        Page<Employee> page = new PageImpl<>(employeeList);

        // Mock the behavior of your service to return the Page<Employee>
        when(employeeService.getAllEmployees(1, 5)).thenReturn(page);

        // Perform the GET request to the endpoint
        ResultActions response = mockMvc.perform(get("/employee")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk()); // Assuming a 200 (OK) status
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].firstName").value("John"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].lastName").value("Doe"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].firstName").value("Jane"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].lastName").value("Smith"));
    }

}
