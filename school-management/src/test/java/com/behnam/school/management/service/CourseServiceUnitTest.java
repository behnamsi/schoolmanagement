package com.behnam.school.management.service;

import com.behnam.school.management.mapper.CourseMapper;
import com.behnam.school.management.model.College;
import com.behnam.school.management.model.Course;
import com.behnam.school.management.model.Professor;
import com.behnam.school.management.newDto.CourseDto;
import com.behnam.school.management.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceUnitTest {

    @Mock
    CourseRepository repository;
    @InjectMocks
    CourseService courseService;

    Course course1;
    Course course2;
    CourseDto courseDto1;
    CourseDto courseDto2;
    College college;
    Professor professor;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(repository,null,null);
        college = new College();
        college.setCollegeId(1L);
        college.setCollegeName("computer");
        professor = new Professor();
        professor.setProfessorId(1L);
        professor.setFirstName("beni");
        professor.setLastName("saqari");
        professor.setNationalId(1234567890L);
        professor.setProfessorId(1234567L);
        course1 = new Course();
        course2 = new Course();
        course1.setCourseName("java");
        course2.setCourseName("cpp");
        course1.setCourseId(1L);
        course2.setCourseId(2L);
        course1.setUnitNumber(3);
        course2.setUnitNumber(3);
        courseDto2 = new CourseDto();
        course1.setProfessor(professor);
        course2.setProfessor(professor);
        course1.setCourseCollege(college);
        course2.setCourseCollege(college);
    }

    @Test
    void getAllCourses() {
        CourseMapper mapper = new CourseMapper();
        Pageable coursePageable = PageRequest.of(0, 3, Sort.by("courseName").ascending());
        List<Course> courseList = new ArrayList<>(List.of(course1, course2));
        Page<Course> coursePage = new PageImpl<>(courseList);
        List<CourseDto> courseDtoListExpected = courseList
                .stream()
                .map(mapper::courseToDto)
                .toList();
        when(repository.findAll(coursePageable))
                .thenReturn(coursePage);
        List<CourseDto> courseDtoListActual = courseService.getAllCourses(1, 3);
        System.out.println("courseDtoListActual = " + courseDtoListActual);
        System.out.println("courseDtoListExpected = " + courseDtoListExpected);
    }
}