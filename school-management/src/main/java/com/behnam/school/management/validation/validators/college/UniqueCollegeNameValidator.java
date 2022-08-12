package com.behnam.school.management.validation.validators.college;

import com.behnam.school.management.configuration.SpringContext;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.validation.annotations.college.UniqueCollegeName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCollegeNameValidator implements ConstraintValidator<UniqueCollegeName, String> {
    @Override
    public void initialize(UniqueCollegeName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        CollegeRepository repository = SpringContext.getBean(CollegeRepository.class);
        return !repository.existsCollegeByCollegeName(name);
    }
}
