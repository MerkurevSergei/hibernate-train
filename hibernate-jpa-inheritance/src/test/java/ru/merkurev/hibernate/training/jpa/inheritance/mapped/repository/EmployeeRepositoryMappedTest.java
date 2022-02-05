package ru.merkurev.hibernate.training.jpa.inheritance.mapped.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.merkurev.hibernate.training.jpa.inheritance.mapped.entity.FullTimeEmployeeMapped;
import ru.merkurev.hibernate.training.jpa.inheritance.mapped.entity.PartTimeEmployeeMapped;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EmployeeRepositoryMappedTest {


    @Autowired
    private EmployeeRepositoryMapped employeeRepository;

    @Test
    @DirtiesContext
    void saveEmployee() {
        employeeRepository.save(new FullTimeEmployeeMapped("First", new BigDecimal(1000)));
        employeeRepository.save(new PartTimeEmployeeMapped("Second", new BigDecimal(20)));
        List<FullTimeEmployeeMapped> fullTimeEmployees = employeeRepository.obtainAllFullTimeEmployee();
        List<PartTimeEmployeeMapped> partTimeEmployees = employeeRepository.obtainAllPartTimeEmployee();
        assertFalse(fullTimeEmployees.isEmpty());
        assertFalse(partTimeEmployees.isEmpty());
    }
}