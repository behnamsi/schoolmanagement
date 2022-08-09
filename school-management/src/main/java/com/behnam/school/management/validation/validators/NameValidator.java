package com.behnam.school.management.validation.validators;

import com.behnam.school.management.validation.annotations.ValidName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {
    @Override
    public void initialize(ValidName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return name.matches("^[a-zA-Z\\\\s]*$") && name.length() >= 3 && name.length() <= 15;
    }
}
