package com.behnam.school.management.validation.validators.professor;

import com.behnam.school.management.configuration.SpringContext;
import com.behnam.school.management.repository.ProfessorRepository;
import com.behnam.school.management.validation.annotations.professor.ValidProfessorId;

import javax.swing.*;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProfessorIdValidator implements ConstraintValidator<ValidProfessorId, Long> {
    @Override
    public void initialize(ValidProfessorId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        ProfessorRepository repository = SpringContext.getBean(ProfessorRepository.class);
        return repository.existsById(id);
    }
}
