package ru.merkurev.hibernate.training.jpa.inheritance;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.merkurev.hibernate.training.jpa.inheritance.joined.entity.FullTimeEmployeeJoined;
import ru.merkurev.hibernate.training.jpa.inheritance.joined.entity.PartTimeEmployeeJoined;
import ru.merkurev.hibernate.training.jpa.inheritance.joined.repository.EmployeeRepositoryJoined;
import ru.merkurev.hibernate.training.jpa.inheritance.singletable.entity.FullTimeEmployee;
import ru.merkurev.hibernate.training.jpa.inheritance.singletable.entity.PartTimeEmployee;
import ru.merkurev.hibernate.training.jpa.inheritance.singletable.repository.EmployeeRepository;
import ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.entity.FullTimeEmployeePerClass;
import ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.entity.PartTimeEmployeePerClass;
import ru.merkurev.hibernate.training.jpa.inheritance.tableperclass.repository.EmployeeRepositoryPerClass;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class HibernateJpaInheritanceApplication implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    private final EmployeeRepositoryPerClass employeeRepositoryPerClass;

    private final EmployeeRepositoryJoined employeeRepositoryJoined;

    public static void main(String[] args) {
        SpringApplication.run(HibernateJpaInheritanceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.save(new FullTimeEmployee("First", new BigDecimal(1000)));
        employeeRepository.save(new PartTimeEmployee("Second", new BigDecimal(20)));

        employeeRepositoryPerClass.save(new FullTimeEmployeePerClass("First", new BigDecimal(1000)));
        employeeRepositoryPerClass.save(new PartTimeEmployeePerClass("Second", new BigDecimal(20)));

        employeeRepositoryJoined.save(new FullTimeEmployeeJoined("First", new BigDecimal(1000)));
        employeeRepositoryJoined.save(new PartTimeEmployeeJoined("Second", new BigDecimal(20)));
    }
}
