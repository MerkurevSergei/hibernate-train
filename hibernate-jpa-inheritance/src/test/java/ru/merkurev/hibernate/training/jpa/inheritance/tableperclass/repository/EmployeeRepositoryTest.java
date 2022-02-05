package ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.entity.EmployeePerClass;
import ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.entity.FullTimeEmployeePerClass;
import ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.entity.PartTimeEmployeePerClass;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepositoryPerClass employeeRepository;

    @Test
    @DirtiesContext
    void saveEmployee() {
        employeeRepository.save(new FullTimeEmployeePerClass("First", new BigDecimal(1000)));
        employeeRepository.save(new PartTimeEmployeePerClass("Second", new BigDecimal(20)));
        List<EmployeePerClass> employees = employeeRepository.obtainAll();
        assertFalse(employees.isEmpty());
    }

}