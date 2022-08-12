package com.behnam.school.management.validation.annotations.professor;

import com.behnam.school.management.validation.validators.professor.ProfessorIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProfessorIdValidator.class)
public @interface ValidProfessorId {
    String message() default "invalid professor id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
