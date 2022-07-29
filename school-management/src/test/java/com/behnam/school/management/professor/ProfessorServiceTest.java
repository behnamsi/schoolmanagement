package com.behnam.school.management.professor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProfessorServiceTest {

    @Autowired
    ProfessorService service;


    @Test
    void getAllProfessors() {
        List<ProfessorDTO> professorDTOS = service.getAllProfessors(1, 3);
        System.out.println("professorDTOS = " + professorDTOS);
    }

    @Test
    void getProfessorStudents() {
        List<String> studentList = service.getProfessorStudents(1L);
        System.out.println("studentList = " + studentList);
    }

    @Test
    void getProfessorStudentsAverages() {
        List<String> avgList = service.getProfessorStudentsAverages(1L);
        System.out.println("avgList = " + avgList);
    }

    @Test
    void getProfessorCourses() {
        List<String> courseList = service.getProfessorsCourses(1L);
        System.out.println("courseList = " + courseList);
    }

    @Test
    void getProfessorStudentsByCourse() {
        List<String> studentList = service.getProfessorStudentsByCourse(1L, "java");
        System.out.println("studentList = " + studentList);
    }

    @Test
    void getProfessorStudentsAveragesByCourse() {
        List<String> studentList = service.getProfessorStudentsAverageByCourse(1L,
                "java");
        System.out.println("studentList = " + studentList);
    }


    @Test
    void addProfessor() {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setFirstName("hadi");
        professorDTO.setLastName("qul");
        professorDTO.setNationalId(123456789L);
        professorDTO.setPersonalId(987654321L);
        service.addProfessor(professorDTO, 1L);
    }

    @Test
    void deleteProfessor() {
        service.deleteProfessor(1L);
    }

    @Test
    void updateProfessor() {
        String firstName = null;
        String lastName = null;
        Long nationalId = null;
        Long personalId = null;
        service.updateProfessor(1L, firstName, lastName, nationalId, personalId);
    }
}