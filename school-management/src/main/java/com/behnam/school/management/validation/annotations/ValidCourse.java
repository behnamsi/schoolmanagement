package com.behnam.school.management.validation.annotations;

import com.behnam.school.management.validation.validators.CourseValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CourseValidator.class)
public @interface ValidCourse {
    String message() default "a valid course must contains\n" +
            "name with length of [3-20] just letters and unit number between 1-3";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
