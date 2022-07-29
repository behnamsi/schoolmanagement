package com.behnam.school.management.professor;

public class ProfessorDTO {
    private String firstName;
    private String lastName;
    private Long personalId;
    private Long nationalId;

    public ProfessorDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    @Override
    public String toString() {
        return "ProfessorDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalId=" + personalId +
                ", nationalId=" + nationalId +
                '}';
    }
}
