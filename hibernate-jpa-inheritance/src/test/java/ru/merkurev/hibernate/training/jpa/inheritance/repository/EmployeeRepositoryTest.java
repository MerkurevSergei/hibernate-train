package ru.merkurev.hibernate.training.jpa.inheritance.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.merkurev.hibernate.training.jpa.inheritance.entity.Employee;
import ru.merkurev.hibernate.training.jpa.inheritance.entity.FullTimeEmployee;
import ru.merkurev.hibernate.training.jpa.inheritance.entity.PartTimeEmployee;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DirtiesContext
    void saveEmployee() {
        employeeRepository.save(new FullTimeEmployee("First", new BigDecimal(1000)));
        employeeRepository.save(new PartTimeEmployee("Second", new BigDecimal(20)));
        List<Employee> employees = employeeRepository.obtainAll();
        assertFalse(employees.isEmpty());
    }

}