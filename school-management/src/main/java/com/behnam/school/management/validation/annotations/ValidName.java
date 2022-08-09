package com.behnam.school.management.validation.annotations;

import com.behnam.school.management.validation.validators.NameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NameValidator.class})
public @interface ValidName {
    String message() default "name must contain only letters and size between 3-20";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
