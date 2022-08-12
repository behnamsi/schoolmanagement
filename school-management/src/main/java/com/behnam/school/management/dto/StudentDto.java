package com.behnam.school.management.dto;

import com.behnam.school.management.validation.annotations.student.ValidStudent;

@ValidStudent
public class StudentDto {
//    @ValidName
    private String firstName;
//    @ValidName
    private String lastName;
    private String collegeName;
//    @ValidNationalId
    private long nationalId;
//    @NotNull
//    @Min(value = 1111111, message = "must be 7 digits ")
//    @Digits(integer = 7, fraction = 0, message = "must be 7 digits")
    private long universityId;

    public StudentDto() {
    }

    public StudentDto(String firstName, String lastName, long nationalId, long universityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.universityId = universityId;
    }

    public StudentDto(String firstName, String lastName, String collegeName, long nationalId, long universityId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.collegeName = collegeName;
        this.nationalId = nationalId;
        this.universityId = universityId;
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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", nationalId=" + nationalId +
                ", universityId=" + universityId +
                '}';
    }
}
