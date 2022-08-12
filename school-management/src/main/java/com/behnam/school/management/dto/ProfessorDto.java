package com.behnam.school.management.dto;

import com.behnam.school.management.validation.annotations.professor.ValidProfessor;

@ValidProfessor
public class ProfessorDto {
    //    @NotNull
//    @Size(min =3,max = 15)
    private String firstName;
    //    @NotNull
//    @Size(min =3,max = 25)
    private String lastName;
    private String collegeName;
    //    @NotNull
//    @Min(value = 1111111,message = "must be 7 digits ")
//    @Digits(integer = 7, fraction = 0, message = "must be 7 digits")
    private Long personalId;
    //    @NotNull
//    @Min(value = 1111111111,message = "must be 10 digits")
//    @Digits(integer = 10, fraction = 0, message = "must be 10 digits")
    private Long nationalId;

    public ProfessorDto() {
    }

    public ProfessorDto(String firstName, String lastName, String collegeName, Long personalId, Long nationalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.collegeName = collegeName;
        this.personalId = personalId;
        this.nationalId = nationalId;
    }

    public ProfessorDto(String firstName, String lastName, Long personalId, Long nationalId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public Long getNationalId() {
        return nationalId;
    }

    public void setNationalId(Long nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public String toString() {
        return "ProfessorDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", personalId=" + personalId +
                ", nationalId=" + nationalId +
                '}';
    }
}
