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
        List<ProfessorDTO> professorDTOS=service.getAllProfessors(1,3);
        System.out.println("professorDTOS = " + professorDTOS);
    }

    @Test
    void addProfessor() {
        ProfessorDTO professorDTO=new ProfessorDTO();
        professorDTO.setFirstName("hadi");
        professorDTO.setLastName("qul");
        professorDTO.setNationalId(123456789L);
        professorDTO.setPersonalId(987654321L);
        service.addProfessor(professorDTO,1L);
    }
}