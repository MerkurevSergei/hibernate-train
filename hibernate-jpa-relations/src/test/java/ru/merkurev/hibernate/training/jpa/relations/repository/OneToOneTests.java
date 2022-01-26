package ru.merkurev.hibernate.training.jpa.relations.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.relations.entity.Passport;
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

    @Test
    void saveStudentWithPassport() {
        Student student = new Student("Vasia");
        student.setPassport(new Passport("7898 546664"));
        studentRepository.save(student);
        assertNotNull(student);
        assertNotNull(student.getPassport());
    }

    @Test
    void changePassportAndSaveStudent() {
        Student student = studentRepository.findById(1L);
        Passport passport = student.getPassport();
        passport.setNumber("Another Number");

        studentRepository.save(student);
        student = studentRepository.findById(1L);

        assertNotNull(student);
        assertNotNull(student.getPassport());
        assertEquals("Another Number", student.getPassport().getNumber());
    }
}