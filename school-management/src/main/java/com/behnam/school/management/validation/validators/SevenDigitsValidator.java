package com.behnam.school.management.validation.validators;

import com.behnam.school.management.validation.annotations.ValidSevenDigits;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SevenDigitsValidator implements ConstraintValidator<ValidSevenDigits,Long> {
    @Override
    public void initialize(ValidSevenDigits constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return aLong.toString().length() == 7 && aLong.toString().charAt(0) != '0';
    }
}
