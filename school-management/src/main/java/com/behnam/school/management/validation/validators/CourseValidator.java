package com.behnam.school.management.validation.validators;

import com.behnam.school.management.dto.CourseDto;
import com.behnam.school.management.validation.annotations.ValidCourse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseValidator implements ConstraintValidator<ValidCourse, CourseDto> {
    @Override
    public void initialize(ValidCourse constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CourseDto courseDto, ConstraintValidatorContext constraintValidatorContext) {
        int nameLength = courseDto.getName().length();
        int unitNumber = courseDto.getUnitNumber();
        return nameLength > 2 && nameLength < 21
                && unitNumber > 0 && unitNumber < 4;
    }
}
