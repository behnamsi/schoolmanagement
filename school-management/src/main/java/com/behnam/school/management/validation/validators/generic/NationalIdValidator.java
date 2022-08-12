package com.behnam.school.management.validation.validators.generic;

import com.behnam.school.management.validation.annotations.generic.ValidNationalId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NationalIdValidator implements ConstraintValidator<ValidNationalId, Long> {
    @Override
    public void initialize(ValidNationalId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong.toString().length() == 10 && aLong.toString().charAt(0) != '0';
    }
}
