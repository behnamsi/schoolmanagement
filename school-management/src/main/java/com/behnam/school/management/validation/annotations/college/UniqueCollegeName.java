package com.behnam.school.management.validation.annotations.college;

import com.behnam.school.management.validation.validators.college.UniqueCollegeNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCollegeNameValidator.class)
public @interface UniqueCollegeName {

    String message() default "the name has taken. name of college must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
