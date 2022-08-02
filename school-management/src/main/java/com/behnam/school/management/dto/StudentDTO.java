package com.behnam.school.management.dto;

import javax.validation.constraints.*;

public class StudentDTO {
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 15, message = "the length must be between 2 and 15 IN DTO")
    private String firstName;
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 25, message = "the length must be between 2 and 25 IN DTO")
    private String lastName;
    @NotNull
    @Min(value = 1111111111,message = "must be 10 digits IN DTO")
    @Digits(integer = 10, fraction = 0, message = "must be 10 digits IN DTO")
    private Long nationalId;
    @NotNull
    @Min(value = 1111111,message = "must be 7 digits IN DTO")
    @Digits(integer = 7, fraction = 0, message = "must be 7 digits IN DTO")
    private Long universityId;


    public StudentDTO() {
    }


    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", universityId=" + universityId +
                ", nationalId=" + nationalId +
                '}';
    }
}
