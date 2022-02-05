package ru.merkurev.hibernate.training.jpa.inheritance.joined.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.merkurev.hibernate.training.jpa.inheritance.joined.entity.EmployeeJoined;
import ru.merkurev.hibernate.training.jpa.inheritance.joined.entity.FullTimeEmployeeJoined;
import ru.merkurev.hibernate.training.jpa.inheritance.joined.entity.PartTimeEmployeeJoined;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class EmployeeRepositoryJoinedTest {

    @Autowired
    private EmployeeRepositoryJoined employeeRepository;

    @Test
    @DirtiesContext
    void saveEmployee() {
        employeeRepository.save(new FullTimeEmployeeJoined("First", new BigDecimal(1000)));
        employeeRepository.save(new PartTimeEmployeeJoined("Second", new BigDecimal(20)));
        List<EmployeeJoined> employees = employeeRepository.obtainAll();
        assertFalse(employees.isEmpty());
    }

}