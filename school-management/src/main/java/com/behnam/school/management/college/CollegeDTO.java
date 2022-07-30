package com.behnam.school.management.college;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CollegeDTO {
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 20)
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
