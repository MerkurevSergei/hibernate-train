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

    @Test
    void saveStudentAndCourse() {
        Student student = new Student("student");
        Course course = new Course("course");
        studentRepository.save(student);
        courseRepository.save(course);

        student.getCourses().add(course);
        course.getStudents().add(student);
        studentRepository.save(student); // владелец связи сохраняет отношение в JoinTable
        courseRepository.save(course); // inverse side не сохраняет отношение в JoinTable
    }

    @Test
    void saveStudentAndCourseSimplify() {
        Student student = new Student("student");
        Course course = new Course("course");
        student.getCourses().add(course);
        course.getStudents().add(student);
        courseRepository.save(course);
        studentRepository.save(student);
    }
}