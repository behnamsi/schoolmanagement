package com.behnam.school.management.validation.annotations.college;

import com.behnam.school.management.validation.validators.college.CollegeIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollegeIdValidator.class)
public @interface ValidCollegeId {
    String message() default "invalid college id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
