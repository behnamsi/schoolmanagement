package com.behnam.school.management.controller;


import com.behnam.school.management.dto.StudentDto;
import com.behnam.school.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;


@RestController
@RequestMapping(path = "api/students")
@Validated
public class StudentController {
    private final StudentService service;

    //constructor
    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    // GET methods

    // get the all of students
    @GetMapping
    public List<StudentDto> getAllStudents(
            @RequestParam(required = false) @Min(1) Integer limit,
            @RequestParam(required = false) @Min(1) Integer page
    ) {
        return service.getAllStudents(limit, page);
    }

    @GetMapping(path = "{studentUniId}")
    public StudentDto getStudent(
            @PathVariable("studentUniId") Long studentUniId
    ) {
        return service.getStudent(studentUniId);
    }

    //get student courses using university id
    @GetMapping(path = "{uniID}/courses")
    public List<String> getStudentCourses(
            @PathVariable("uniID") @Min(1111111) Long uniID
    ) {
        return service.getStudentCourses(uniID);
    }

    // get student average
    @GetMapping(path = "{uniID}/averages")
    public Double getStudentAverage(@PathVariable("uniID") @Min(1) Long uniID) {
        return service.getStudentAverage(uniID);
    }


    //POST methods
    @PostMapping
    public void addStudent(
            @Valid @RequestBody StudentDto student,
            @RequestParam @Min(value = 1, message = "college id starts at 1") Long collegeId
    ) {
        service.addStudent(student, collegeId);
    }


//    @DeleteMapping(path = "{id}/delete")
//    public void deleteStudent(
//            @PathVariable("id") @Min(1) Long id
//    ) {
//        service.deleteStudent(id);
//    }

    @DeleteMapping(path = "{uniId}")
    public void deleteStudentByUniId(
            @PathVariable("uniId") @Min(1111111) Long uniId
    ) {

        service.deleteStudentByUniId(uniId);
    }

    // update with university id
    @PutMapping(path = "{uniId}")
    public void updateStudent(
            @PathVariable("uniId") @Min(1111111) Long uniId,
            @RequestParam(required = false) @NotEmpty @Size(min = 3, max = 15) String first_name,
            @RequestParam(required = false) @NotEmpty @Size(min = 3, max = 25) String last_name,
            @RequestParam(required = false) @NotEmpty List<String> courses,
            @RequestParam(required = false) @Min(1111111111) Long nationalId
    ) {
        service.updateStudent(uniId, first_name, last_name, courses, nationalId);
    }

    // add course for student
    @PutMapping(path = "{uniId}/courses/{courseName}/scores")
    public void addScoreCourse(
            @PathVariable("uniId") @Min(1111111) Long uniId,
            @PathVariable("courseName") @NotEmpty @Size(min = 1, max = 20) String courseName,
            @RequestParam @Min(0) @Max(20) Double score
    ) {
        service.addScoreCourse(uniId, courseName, score);
    }

    // delete course for student
    @PutMapping(path = "{uniId}/courses/delete")
    public void deleteStudentCourse(
            @PathVariable("uniId") @Min(1111111) Long uniId,
            @RequestParam @NotEmpty @Size(min = 1, max = 20) String courseName
    ) {
        service.deleteStudentCourse(uniId, courseName);
    }
}
