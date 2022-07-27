package com.behnam.school.management.student;

import com.behnam.school.management.college.College;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Test
    void getAllStudents() {
        System.out.println(service.getAllStudents(10, 1));
    }

    @Test
    void addStudent() {
        Student student = new Student
                ("beni",
                        "si",
                        1L,
                        22L);
        service.addStudent(student, 1L);
    }

    @Test
    void deleteStudent() {
        service.deleteStudent(8L);
    }

    @Test
    void deleteStudentByUniId() {
        service.deleteStudentByUniId(22L);
    }

    @Test
    void updateStudent() {
        List<String> courses = new ArrayList<>();
        courses.add("algorithem");
        service.updateStudent(22L, null,
                null, courses, null, null);
    }

    @Test
    void getStudentCourses() {
        System.out.println(service.getStudentCourses(22L));
    }

    @Test
    void addScoreCourse() {
        service.addScoreCourse(22L,
                "algorithem",
                20.0);
    }

    @Test
    void deleteStudentCourse() {
        service.deleteStudentCourse(22L,"algorithem");
    }

    @Test
    void getStudentAverage() {
        System.out.println("average of 22 = " + service.getStudentAverage(22L));
    }
}