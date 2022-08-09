package com.behnam.school.management.validation.annotations;

import com.behnam.school.management.validation.validators.CollegeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollegeValidator.class)
public @interface ValidCollege {
    String message() default "a valid college must contains\n" +
            "name with length of [3-20] just letters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
