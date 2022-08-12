package com.behnam.school.management.validation.annotations.course;

import com.behnam.school.management.validation.validators.course.CourseIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CourseIdValidator.class)
public @interface ValidCourseId {

    String message() default "invalid course id.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
