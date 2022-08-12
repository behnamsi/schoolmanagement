package com.behnam.school.management.validation.annotations.course;

import com.behnam.school.management.validation.validators.course.UniqueCourseNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCourseNameValidator.class)
public @interface UniqueCourseName {

    String message() default "course name is unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
