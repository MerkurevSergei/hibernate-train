package ru.merkurev.hibernate.training.jpa.relations.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.relations.entity.Student;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OneToOneTests {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findById() {
        Student student = studentRepository.findById(1L);
        assertNotNull(student);
        assertNotNull(student.getPassport());
    }
}