package com.behnam.school.management.course;

import javax.validation.constraints.*;

public class CourseDTO {
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 20)
    private String courseName;
    @Min(1) @Max(3)
    @NotNull
    private Integer unitNumber;

    public CourseDTO() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseName='" + courseName + '\'' +
                ", courseUnit=" + unitNumber +
                '}';
    }
}
