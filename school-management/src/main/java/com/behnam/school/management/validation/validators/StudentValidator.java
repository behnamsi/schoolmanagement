package com.behnam.school.management.validation.validators;

import com.behnam.school.management.dto.StudentDto;
import com.behnam.school.management.validation.annotations.ValidStudent;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StudentValidator implements ConstraintValidator<ValidStudent, StudentDto> {
    @Override
    public void initialize(ValidStudent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(StudentDto studentDto, ConstraintValidatorContext constraintValidatorContext) {
        String nationalId = Long.toString(studentDto.getNationalId());
        String uniId = Long.toString(studentDto.getUniversityId());
        return studentDto.getFirstName().length() > 2 && studentDto.getFirstName().length() < 21
                && studentDto.getLastName().length() > 2 && studentDto.getLastName().length() < 21
                && nationalId.length() == 10 && uniId.length() == 7
                && studentDto.getFirstName().matches("^[a-zA-Z\\\\s]*$")
                && studentDto.getLastName().matches("^[a-zA-Z\\\\s]*$");
    }
}
