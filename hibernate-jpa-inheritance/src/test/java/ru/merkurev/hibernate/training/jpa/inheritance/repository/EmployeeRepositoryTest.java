package ru.merkurev.hibernate.training.jpa.inheritance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.inheritance.entity.Employee;
import ru.merkurev.hibernate.training.jpa.inheritance.entity.FullTimeEmployee;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void saveEmployee() {
        employeeRepository.save(new FullTimeEmployee("First", new BigDecimal(1000)));
        List<Employee> employees = employeeRepository.obtainAll();
        assertFalse(employees.isEmpty());
    }

}