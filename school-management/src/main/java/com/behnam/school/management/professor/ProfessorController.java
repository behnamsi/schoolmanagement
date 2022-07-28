package com.behnam.school.management.professor;


import com.behnam.school.management.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;

@RestController
@RequestMapping("api/professors")
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
    public List<ProfessorDTO> getAllProfessors(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer page
    ) {
        return service.getAllProfessors(page,limit);
    }

    // get all students that belong to a professor
    @GetMapping(path = "{professorId}/get-students")
    public List<String> getProfessorStudents(
            @PathVariable("professorId") Long professorId
    ) {
        return service.getProfessorStudents(professorId);
    }

    // get all students averages that belong to a professor
    @GetMapping(path = "{professorId}/get-students/avg")
    public List<String> getProfessorStudentsAverages(
            @PathVariable("professorId") Long professorId
    ) {
        return service.getProfessorStudentsAverages(professorId);
    }

    // get courses of a professor
    @GetMapping(path = "{professorId}/course")
    public List<String> getProfessorsCourses(
            @PathVariable("professorId") Long professorId
    ) {
        return service.getProfessorsCourses(professorId);
    }
    // get students of a course of professor
    @GetMapping(path = "{professorId}/get-students-of-a-course/{courseName}")
    public List<String> getProfessorStudentsByCourse(
            @PathVariable("courseName") String courseName,
            @PathVariable("professorId") Long professorId
    ){
        return service.getProfessorStudentsByCourse(professorId,courseName);
    }
    @GetMapping(path = "{professorId}/get-students-of-a-course/avg/{courseName}")
    public List<String> getProfessorStudentsAverageByCourse(
            @PathVariable("courseName") String courseName,
            @PathVariable("professorId") Long professorId
    ){
        return service.getProfessorStudentsAverageByCourse(professorId,courseName);
    }


    //POST methods:

    @PostMapping(path = "add/")
    public void addProfessor(
            @RequestBody Professor professor,
            @RequestParam() Long collegeId
    ) {
        service.addProfessor(professor, collegeId);
    }

    @DeleteMapping(path = "{profId}/delete")
    public void deleteProfessor(
            @PathVariable("profId") Long id
    ) {
        service.deleteProfessor(id);
    }

    @PutMapping(path = "{profId}/update")
    public void updateProfessor(
            @PathVariable("profId") Long id,
            @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name,
            @RequestParam(required = false) Long nationalId,
            @RequestParam(required = false) Long personalId
    ) {
        service.updateProfessor(id, first_name, last_name, nationalId, personalId);
    }
}
