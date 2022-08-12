package com.behnam.school.management.validation.validators.college;

import com.behnam.school.management.configuration.SpringContext;
import com.behnam.school.management.model.College;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.validation.annotations.college.ValidCollegeId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CollegeIdValidator implements ConstraintValidator<ValidCollegeId, Long> {
    @Override
    public void initialize(ValidCollegeId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        CollegeRepository repository= SpringContext.getBean(CollegeRepository.class);
        return repository.existsById(id);
    }
}
