package com.behnam.school.management.controller;

import com.behnam.school.management.dto.CourseDTO;
import com.behnam.school.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/courses")
public class CourseController {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(
            @RequestParam(required = false) @Min(1) Integer page,
            @RequestParam(required = false) @Min(1) Integer limit
    ) {
        return service.getAllCourses(page, limit);
    }

    @PostMapping
    public void addCourse(@Valid @RequestBody CourseDTO course,
                          @RequestParam() @Min(1) Long professorId,
                          @RequestParam() @Min(1) Long collegeId
    ) {
        service.addCourse(course, professorId, collegeId);
    }

    @DeleteMapping("{courseName}")
    public void deleteCourseByName(
            @PathVariable("courseName") @NotEmpty @Size(min = 1, max = 20) String courseName
    ) {
        service.deleteCourseByName(courseName);
    }

//    @DeleteMapping(path = "{courseId}/delete-id")
//    public void deleteCourseById(
//            @PathVariable("courseId") @Min(1) Long courseId
//    ) {
//        service.deleteCourseById(courseId);
//    }

    @PutMapping(path = "{courseId}")
    public void updateCourse(
            @PathVariable("courseId") @Min(1) Long courseId,
            @RequestParam(required = false) @NotEmpty @Size(min = 1, max = 20) String courseName,
            @RequestParam(required = false) @Min(1) @Max(3) Integer unitNumber,
            @RequestParam(required = false) @Min(1) Long professorId
    ) {
        service.updateCourse(courseId, courseName, unitNumber, professorId);
    }
}
