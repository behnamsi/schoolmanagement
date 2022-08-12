package com.behnam.school.management.validation.validators.student;

import com.behnam.school.management.configuration.SpringContext;
import com.behnam.school.management.repository.StudentRepository;
import com.behnam.school.management.validation.annotations.student.ValidStudentUniversityId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentIdValidator implements ConstraintValidator<ValidStudentUniversityId,Long> {
    @Override
    public void initialize(ValidStudentUniversityId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long universityId, ConstraintValidatorContext constraintValidatorContext) {
        StudentRepository repository= SpringContext.getBean(StudentRepository.class);
        return repository.existsByUniversityId(universityId);
    }
}
