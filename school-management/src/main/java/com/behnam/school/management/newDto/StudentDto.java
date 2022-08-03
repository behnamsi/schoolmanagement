package com.behnam.school.management.newDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDto {
    @NotNull
    @Size(min =3,max = 15)
    private String firstName;
    @NotNull
    @Size(min = 3,max = 25)
    private String lastName;
    private String collegeName;
    @NotNull
    private long nationalId;
    @NotNull
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
