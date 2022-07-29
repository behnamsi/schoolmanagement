package com.behnam.school.management.student;

public class StudentDTO {
    private String firstName;
    private String lastName;
    private Long universityId;
    private Long nationalId;

    public StudentDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
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
