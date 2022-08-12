package com.behnam.school.management.validation.validators.college;

import com.behnam.school.management.configuration.SpringContext;
import com.behnam.school.management.dto.CollegeDto;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.validation.annotations.college.ValidCollege;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CollegeValidator implements ConstraintValidator<ValidCollege, CollegeDto> {
    @Override
    public void initialize(ValidCollege constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CollegeDto collegeDto, ConstraintValidatorContext constraintValidatorContext) {
        CollegeRepository repository = SpringContext.getBean(CollegeRepository.class);
        if (repository.existsCollegeByCollegeName(collegeDto.getName())) return false;
        int nameLength = collegeDto.getName().length();
        return nameLength > 2 && nameLength < 21;
    }
}
