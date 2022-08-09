package com.behnam.school.management.dto;

import com.behnam.school.management.validation.annotations.ValidCollege;

import java.util.List;

@ValidCollege
public class CollegeDto {
    private String name;
    private List<String> courses;

    public CollegeDto() {
    }

    public CollegeDto(String name, List<String> courses) {
        this.name = name;
        this.courses = courses;
    }

    public CollegeDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CollegeDto{" +
                "name='" + name + '\'' +
                ", courses=" + courses +
                '}';
    }
}

