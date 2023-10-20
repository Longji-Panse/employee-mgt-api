package com.demo.springbootemployeeapi.repository;

import com.demo.springbootemployeeapi.model.Employee;
import com.demo.springbootemployeeapi.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTest {
    //@Mock
    @Autowired
    EmployeeRepository employeeRepository;
  @Test
    public void EmployeeRepository_Save_ReturnSavedEmployee(){
        //Arrange
        Employee employee = Employee.builder().firstName("john").lastName("cena").email("jc@c.com").build();
        //when(employeeRepository.save(employee)).thenReturn(employee);
        //Act
        Employee savedEmployee = employeeRepository.save(employee);
        //Assert
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

}
