package com.behnam.school.management.service;

import com.behnam.school.management.dto.StudentDTO;
import com.behnam.school.management.model.College;
import com.behnam.school.management.model.Student;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceUnitTest {

    private StudentService service;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CollegeRepository collegeRepository;

    @BeforeEach
    void setUp() {
        service = new StudentService(studentRepository, collegeRepository, null);
    }

    @Test
    void addStudent() {

    }

    @Test
    void getAllStudents() {
        System.err.println(studentRepository.findAll());
        service.getAllStudents(3,1);
    }

    @Test
    void deleteStudentByUniId() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void getStudentCourses() {
    }

    @Test
    void addScoreCourse() {
    }

    @Test
    void deleteStudentCourse() {
    }

    @Test
    void getStudentAverage() {
    }
}