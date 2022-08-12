package com.behnam.school.management.controller;


import com.behnam.school.management.dto.ProfessorDto;
import com.behnam.school.management.service.ProfessorService;
import com.behnam.school.management.validation.annotations.college.ValidCollegeId;
import com.behnam.school.management.validation.annotations.generic.ValidName;
import com.behnam.school.management.validation.annotations.generic.ValidNationalId;
import com.behnam.school.management.validation.annotations.generic.ValidSevenDigits;
import com.behnam.school.management.validation.annotations.professor.ValidProfessorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("api/professors")
@Validated
public class ProfessorController {

    private final ProfessorService service;

    @Autowired
    public ProfessorController(ProfessorService service) {
        this.service = service;
    }


    // methods:

    // GET methods:

    //get All Professors
    @GetMapping
    public List<ProfessorDto> getAllProfessors(
            @RequestParam(required = false) @Min(1) Integer limit,
            @RequestParam(required = false) @Min(1) Integer page
    ) {
        return service.getAllProfessors(page, limit);
    }

    @GetMapping(path = "{profId}")
    public ProfessorDto getProfessor(
            @PathVariable("profId") @ValidProfessorId Long profId
    ) {
        return service.getProfessor(profId);
    }

    // get all students that belong to a professor
    @GetMapping(path = "{professorId}/students")
    public List<String> getProfessorStudents(
            @PathVariable("professorId") @ValidProfessorId Long professorId
    ) {
        return service.getProfessorStudents(professorId);
    }

    // get all students averages that belong to a professor
    @GetMapping(path = "{professorId}/students/averages")
    public List<String> getProfessorStudentsAverages(
            @PathVariable("professorId") @ValidProfessorId Long professorId
    ) {
        return service.getProfessorStudentsAverages(professorId);
    }

    // get courses of a professor
    @GetMapping(path = "{professorId}/courses")
    public List<String> getProfessorsCourses(
            @PathVariable("professorId") @ValidProfessorId Long professorId
    ) {
        return service.getProfessorsCourses(professorId);
    }

    // get students of a course of professor
    @GetMapping(path = "{professorId}/courses/{courseName}/students")
    public List<String> getProfessorStudentsByCourse(
            @PathVariable("courseName") @ValidName String courseName,
            @PathVariable("professorId") @ValidProfessorId Long professorId
    ) {
        return service.getProfessorStudentsByCourse(professorId, courseName);
    }

    @GetMapping(path = "{professorId}/courses/{courseName}/students/averages")
    public List<String> getProfessorStudentsAverageByCourse(
            @PathVariable("courseName") @ValidName String courseName,
            @PathVariable("professorId") @ValidProfessorId Long professorId
    ) {
        return service.getProfessorStudentsAverageByCourse(professorId, courseName);
    }


    //POST methods:

    @PostMapping
    public void addProfessor(
            @Valid @RequestBody ProfessorDto professorDto,
            @RequestParam() @ValidCollegeId Long collegeId
    ) {
        service.addProfessor(professorDto, collegeId);
    }

    @DeleteMapping(path = "{profId}")
    public void deleteProfessor(
            @PathVariable("profId") @ValidProfessorId Long id
    ) {
        service.deleteProfessor(id);
    }

    @PutMapping(path = "{profId}")
    public void updateProfessor(
            @PathVariable("profId") @ValidProfessorId Long id,
            @RequestParam(required = false) @ValidName String first_name,
            @RequestParam(required = false) @ValidName String last_name,
            @RequestParam(required = false) @ValidNationalId Long nationalId,
            @RequestParam(required = false) @ValidSevenDigits Long personalId
    ) {
        service.updateProfessor(id, first_name, last_name, nationalId, personalId);
    }
}
