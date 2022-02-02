package ru.merkurev.hibernate.training.jpa.relations.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.merkurev.hibernate.training.jpa.relations.entity.Course;
import ru.merkurev.hibernate.training.jpa.relations.entity.Review;
import ru.merkurev.hibernate.training.jpa.relations.entity.Student;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ManyToManyTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void getCourseWithStudents() {
        Course course = courseRepository.findById(1L);
        List<Student> students = course.getStudents();
        assertFalse(students.isEmpty());
    }

    @Test
    @Transactional
    void getCoursesFromStudentInTransaction() {
        Student student = studentRepository.findById(1L);
        List<Course> courses = student.getCourses();
        assertFalse(courses.isEmpty());
    }
}