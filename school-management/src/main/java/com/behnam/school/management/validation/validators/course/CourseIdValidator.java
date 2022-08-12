package com.behnam.school.management.validation.validators.course;

import com.behnam.school.management.configuration.SpringContext;
import com.behnam.school.management.repository.CourseRepository;
import com.behnam.school.management.validation.annotations.course.ValidCourseId;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseIdValidator implements ConstraintValidator<ValidCourseId, Long> {
    @Override
    public void initialize(ValidCourseId constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        CourseRepository repository = SpringContext.getBean(CourseRepository.class);
        return repository.existsById(id);
    }
}
