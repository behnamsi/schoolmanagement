package com.behnam.school.management.course;

import com.behnam.school.management.professor.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/courses/")
public class CourseController {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getAllCourses(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ) {
        return service.getAllCourses(page, limit);
    }

    @PostMapping(path = "add/")
    public void addCourse(@RequestBody Course course,
                          @RequestParam() Long professorId,
                          @RequestParam() Long collegeId
    ) {
        service.addCourse(course, professorId, collegeId);
    }

    @DeleteMapping("{courseName}/delete-name")
    public void deleteCourseByName(
            @PathVariable("courseName") String courseName
    ) {
        service.deleteCourseByName(courseName);
    }

    @DeleteMapping(path = "{courseId}/delete-id")
    public void deleteCourseById(
            @PathVariable("courseId") Long courseId
    ) {
        service.deleteCourseById(courseId);
    }

    @PutMapping(path = "{courseId}/update")
    public void updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer unitNumber,
            @RequestParam(required = false) Long professorId
    ) {
        service.updateCourse(courseId, courseName, unitNumber, professorId);
    }
}
