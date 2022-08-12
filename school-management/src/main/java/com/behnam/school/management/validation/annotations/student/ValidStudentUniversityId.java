package com.behnam.school.management.validation.annotations.student;

import com.behnam.school.management.validation.validators.student.StudentIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentIdValidator.class)
public @interface ValidStudentUniversityId {
    String message() default "invalid student uni id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
