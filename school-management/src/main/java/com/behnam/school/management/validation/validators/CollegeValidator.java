package com.behnam.school.management.validation.validators;

import com.behnam.school.management.dto.CollegeDto;
import com.behnam.school.management.validation.annotations.ValidCollege;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CollegeValidator implements ConstraintValidator<ValidCollege, CollegeDto> {
    @Override
    public void initialize(ValidCollege constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CollegeDto collegeDto, ConstraintValidatorContext constraintValidatorContext) {
        int nameLength = collegeDto.getName().length();
        return nameLength > 2 && nameLength < 21;
    }
}
