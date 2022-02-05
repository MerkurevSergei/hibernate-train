package ru.merkurev.hibernate.training.jpa.inheritance;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.merkurev.hibernate.training.jpa.inheritance.singletable.entity.FullTimeEmployee;
import ru.merkurev.hibernate.training.jpa.inheritance.singletable.entity.PartTimeEmployee;
import ru.merkurev.hibernate.training.jpa.inheritance.singletable.repository.EmployeeRepository;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class HibernateJpaInheritanceApplication implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(HibernateJpaInheritanceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.save(new FullTimeEmployee("First", new BigDecimal(1000)));
        employeeRepository.save(new PartTimeEmployee("Second", new BigDecimal(20)));    }
}
