package com.behnam.school.management.controller;


import com.behnam.school.management.dto.CourseDto;
import com.behnam.school.management.service.CourseService;
import com.behnam.school.management.validation.annotations.generic.ValidName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/courses")
@Validated
public class CourseController {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<CourseDto> getAllCourses(
            @RequestParam(required = false) @Min(1) Integer page,
            @RequestParam(required = false) @Min(1) Integer limit
    ) {
        return service.getAllCourses(page, limit);
    }

    @GetMapping(path = "{courseId}")
    public CourseDto getCourse(
            @PathVariable("courseId") @Min(1) Long courseId
    ) {
        return service.getCourse(courseId);
    }

    @PostMapping
    public void addCourse(@Valid @RequestBody CourseDto courseDto,
                          @RequestParam() @Min(1) Long professorId,
                          @RequestParam() @Min(1) Long collegeId
    ) {
        service.addCourse(courseDto, professorId, collegeId);
    }

    @DeleteMapping("{courseName}")
    public void deleteCourseByName(
            @PathVariable("courseName") @ValidName String courseName
    ) {
        service.deleteCourseByName(courseName);
    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(
            @PathVariable("courseId") @Min(1) Long courseId,
            @RequestParam(required = false) @ValidName String courseName,
            @RequestParam(required = false) @Min(1) @Max(3) Integer unitNumber,
            @RequestParam(required = false) Long professorId
    ) {
        service.updateCourse(courseId, courseName, unitNumber, professorId);
    }
}
