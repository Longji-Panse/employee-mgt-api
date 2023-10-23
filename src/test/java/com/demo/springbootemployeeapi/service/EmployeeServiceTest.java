package com.demo.springbootemployeeapi.service;

import com.demo.springbootemployeeapi.model.Employee;
import com.demo.springbootemployeeapi.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void EmployeeService_SaveEmployee_ReturnEmployee(){
        Employee employee = Employee.builder()
                .firstName("john")
                .lastName("cena")
                .email("jc@c.com").build();

        when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        //act
        Employee savedEmployee = employeeService.saveEmployee(employee);

        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    @Test
    public void EmployeeService_GetAllEmployees_ReturnPageable(){
        Page<Employee> employees = Mockito.mock(Page.class);
        when(employeeRepository.findAll(Mockito.any(Pageable.class))).thenReturn(employees);

        Page<Employee> allEmployees = employeeService.getAllEmployees(1,5);

        Assertions.assertThat(allEmployees).isNotNull();
    }

    @Test
    public void EmployeeService_FindAllPaginated_ReturnPageable(){
        Page<Employee> employees = Mockito.mock(Page.class);
        when(employeeRepository.findAll(Mockito.any(Pageable.class))).thenReturn(employees);

        Page<Employee> allEmployees = employeeService.findAllPaginated(1,5);

        Assertions.assertThat(allEmployees).isNotNull();

    }

    @Test
    public void EmployeeService_GetEmployeeById_ReturnEmployee(){
        //Arrange
        long employeeId = 1;
        Employee employee = Employee.builder()
                .firstName("john")
                .lastName("cena")
                .email("jc@c.com").build();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.ofNullable(employee));
        //Act
        Employee savedEmployee = employeeService.getEmployeeById(employeeId);
        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
    }

    @Test
    public void EmployeeService_DeleteEmployeeById_ReturnNothing(){
        //Arrange
        long employeeId = 1;
        Employee employee = Employee.builder()
                .firstName("john")
                .lastName("cena")
                .email("jc@wwe.com").build();
        //Act & Assert
        assertAll(() -> employeeService.deleteEmployeeById(employeeId));
    }
}
