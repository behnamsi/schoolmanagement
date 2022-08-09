package com.behnam.school.management.dto;

import com.behnam.school.management.validation.annotations.ValidCourse;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ValidCourse
public class CourseDto {
//    @NotNull
//    @Size(min = 1, max = 20)
    private String name;
//    @NotNull
//    @Min(1)
//    @Max(3)
    private Integer unitNumber;
    private String professorOfCourse;
    private String collegeName;

    public CourseDto(String name, Integer unitNumber, String professorOfCourse, String collegeName) {
        this.name = name;
        this.unitNumber = unitNumber;
        this.professorOfCourse = professorOfCourse;
        this.collegeName = collegeName;
    }

    public CourseDto(String name, Integer unitNumber) {
        this.name = name;
        this.unitNumber = unitNumber;
    }

    public CourseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getProfessorOfCourse() {
        return professorOfCourse;
    }

    public void setProfessorOfCourse(String professorOfCourse) {
        this.professorOfCourse = professorOfCourse;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "name='" + name + '\'' +
                ", unitNumber=" + unitNumber +
                ", professorOfCourse='" + professorOfCourse + '\'' +
                ", collegeName='" + collegeName + '\'' +
                '}';
    }
}
