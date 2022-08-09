package com.behnam.school.management.validation.annotations;


import com.behnam.school.management.validation.validators.NationalIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NationalIdValidator.class)
public @interface ValidNationalId {
    String message() default "national id must be 10 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
