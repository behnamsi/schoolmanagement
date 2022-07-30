package com.behnam.school.management.student;

import com.behnam.school.management.college.College;
import com.behnam.school.management.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;


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
    public List<StudentDTO> getAllStudents(
            @RequestParam(required = false) @Min(1) Integer limit,
            @RequestParam(required = false) @Min(1) Integer page
    ) {
        return service.getAllStudents(limit, page);
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
    @PostMapping(path = "add")
    public void addStudent(
            @Valid @RequestBody StudentDTO student,
            @RequestParam @Min(1) Long collegeId
    ) {
        service.addStudent(student, collegeId);
    }


    @DeleteMapping(path = "{id}/delete")
    public void deleteStudent(
            @PathVariable("id") @Min(1) Long id
    ) {
        service.deleteStudent(id);
    }

    @DeleteMapping(path = "{uniId}/delete/university-id/")
    public void deleteStudentByUniId(
            @PathVariable("uniId") @Min(1111111) Long uniId
    ) {

        service.deleteStudentByUniId(uniId);
    }

    // update with university id
    @PutMapping(path = "{uniId}/update")
    public void updateStudent(
            @PathVariable("uniId") @Min(1111111) Long uniId,
            @RequestParam(required = false) @NotEmpty @Size(min = 3, max = 15) String first_name,
            @RequestParam(required = false) @NotEmpty @Size(min = 3, max = 25) String last_name,
            @RequestParam(required = false) @NotEmpty List<String> courses,
            @RequestParam(required = false) @Min(1111111111) Long nationalId,
            @RequestParam(required = false) @Min(1111111) Long universityId
    ) {
        service.updateStudent(uniId, first_name, last_name, courses, nationalId, universityId);
    }

    // add course for student
    @PutMapping(path = "{uniId}/score/{courseName}")
    public void addScoreCourse(
            @PathVariable("uniId") @Min(1111111) Long uniId,
            @PathVariable("courseName") @NotEmpty @Size(min = 1, max = 20) String courseName,
            @RequestParam @Min(0) @Max(20) Double score
    ) {
        service.addScoreCourse(uniId, courseName, score);
    }

    // delete course for student
    @PutMapping(path = "{uniId}/course/delete")
    public void deleteStudentCourse(
            @PathVariable("uniId") @Min(1111111) Long uniId,
            @RequestParam @NotEmpty @Size(min = 1, max = 20) String courseName
    ) {
        service.deleteStudentCourse(uniId, courseName);
    }
}
