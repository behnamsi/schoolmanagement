package com.behnam.school.management.college;

public class CollegeDTO {
    private String collegeName;

    public CollegeDTO() {
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Override
    public String toString() {
        return "CollegeDTO{" +
                "collegeName='" + collegeName + '\'' +
                '}';
    }
}
