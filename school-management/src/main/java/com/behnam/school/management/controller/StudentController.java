package com.behnam.school.management.controller;


import com.behnam.school.management.dto.StudentDto;
import com.behnam.school.management.service.StudentService;
import com.behnam.school.management.validation.annotations.ValidName;
import com.behnam.school.management.validation.annotations.ValidNationalId;
import com.behnam.school.management.validation.annotations.ValidSevenDigits;
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
            @PathVariable("uniID") @ValidSevenDigits Long uniID
    ) {
        return service.getStudentCourses(uniID);
    }

    // get student average
    @GetMapping(path = "{uniID}/averages")
    public Double getStudentAverage(@PathVariable("uniID") @ValidSevenDigits Long uniID) {
        return service.getStudentAverage(uniID);
    }


    //POST methods
    @PostMapping
    public void addStudent(
            @Valid @RequestBody StudentDto student,
            @RequestParam @Min(1) Long collegeId
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
            @PathVariable("uniId") @ValidSevenDigits Long uniId
    ) {

        service.deleteStudentByUniId(uniId);
    }

    // update with university id
    @PutMapping(path = "{uniId}")
    public void updateStudent(
            @PathVariable("uniId") @ValidSevenDigits Long uniId,
            @RequestParam(required = false) @ValidName String first_name,
            @RequestParam(required = false) @ValidName String last_name,
            @RequestParam(required = false) @NotEmpty List<String> courses,
            @RequestParam(required = false) @ValidNationalId Long nationalId
    ) {
        service.updateStudent(uniId, first_name, last_name, courses, nationalId);
    }

    // add course for student
    @PutMapping(path = "{uniId}/courses/{courseName}/scores")
    public void addScoreCourse(
            @PathVariable("uniId") @ValidSevenDigits Long uniId,
            @PathVariable("courseName") @ValidName String courseName,
            @RequestParam @Min(0) @Max(20) Double score
    ) {
        service.addScoreCourse(uniId, courseName, score);
    }

    // delete course for student
    @PutMapping(path = "{uniId}/courses/delete")
    public void deleteStudentCourse(
            @PathVariable("uniId") @ValidSevenDigits Long uniId,
            @RequestParam @ValidName String courseName
    ) {
        service.deleteStudentCourse(uniId, courseName);
    }
}
