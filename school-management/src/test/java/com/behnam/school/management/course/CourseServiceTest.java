package com.behnam.school.management.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseService service;

    @Test
    void getAllCourses() {
        System.out.println(service.getAllCourses(1,5));
    }

    @Test
    void addCourse() {

    }


}