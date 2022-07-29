package com.behnam.school.management.student;

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
        List<StudentDTO> studentDTOS = service.getAllStudents(15, 1);
        System.out.println("studentDTOS = " + studentDTOS);
    }

    @Test
    void getStudentCourses() {
        List<String> courseList = service.getStudentCourses(9261156L);
        System.out.println("courseList = " + courseList);
    }

    @Test
    void getStudentAverage() {
        Double average = service.getStudentAverage(9261156L);
        System.out.println("average = " + average);
    }

    @Test
    void addStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("abbas");
        studentDTO.setLastName("bu azar");
        studentDTO.setUniversityId(123434431L);
        studentDTO.setNationalId(21811872634L);
        service.addStudent(studentDTO, 1L);
    }

    @Test
    void deleteStudentById() {
        service.deleteStudent(14L);
    }

    @Test
    void deleteStudentByUniId() {
        service.deleteStudentByUniId(9261156L);
    }

    @Test
    void updateStudent() {
        String firstName = null;
        String lastName = null;
        List<String> courses = new ArrayList<>();
        courses.add("algorithem");
        courses.add("java");
        Long nationalId = null;
        Long universituId = null;

        service.updateStudent(9261156L, firstName, lastName, courses, nationalId, universituId);
    }

    @Test
    void addScoreCourse() {
        service.addScoreCourse(9261156L, "java", 20D);
        service.addScoreCourse(9261156L, "algorithem", 20D);
    }

    @Test
    void deleteStudentCourse(){
        service.deleteStudentCourse(9261156L,"algorithem");
    }


}