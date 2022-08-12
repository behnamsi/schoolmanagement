package com.behnam.school.management.validation.validators.course;

import com.behnam.school.management.configuration.SpringContext;
import com.behnam.school.management.repository.CourseRepository;
import com.behnam.school.management.validation.annotations.course.UniqueCourseName;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCourseNameValidator implements ConstraintValidator<UniqueCourseName, String> {
    @Override
    public void initialize(UniqueCourseName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        CourseRepository repository = SpringContext.getBean(CourseRepository.class);
        return !repository.existsCourseByCourseName(name);

    }
}
