package com.behnam.school.management.validation.annotations;

import com.behnam.school.management.validation.validators.ProfessorValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProfessorValidator.class)
public @interface ValidProfessor {
    String message() default """
            a valid professor must contain\s
            first name and last name chars between 3-20 and contain just letters
            10 digits national id
            7 personal id""";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
