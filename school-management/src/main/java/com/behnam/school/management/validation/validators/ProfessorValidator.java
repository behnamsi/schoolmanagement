package com.behnam.school.management.validation.validators;

import com.behnam.school.management.dto.ProfessorDto;
import com.behnam.school.management.validation.annotations.ValidProfessor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProfessorValidator implements ConstraintValidator<ValidProfessor, ProfessorDto> {
    @Override
    public void initialize(ValidProfessor constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ProfessorDto professorDto, ConstraintValidatorContext constraintValidatorContext) {
        String nationalId = Long.toString(professorDto.getNationalId());
        String personalId = Long.toString(professorDto.getPersonalId());
        return professorDto.getFirstName().length() > 2 && professorDto.getFirstName().length() < 21
                && professorDto.getLastName().length() > 2 && professorDto.getLastName().length() < 21
                && nationalId.length() == 10 && personalId.length() == 7
                && professorDto.getFirstName().matches("^[a-zA-Z\\\\s]*$")
                && professorDto.getLastName().matches("^[a-zA-Z\\\\s]*$");
    }
}
