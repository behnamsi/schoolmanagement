package com.behnam.school.management.professor;

import com.behnam.school.management.college.College;
import com.behnam.school.management.course.Course;
import com.behnam.school.management.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table
public class Professor {

    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(updatable = false)
    private Long professorId;
    @Column(nullable = false, length = 15)
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 15, message = "the length must be between 2 and 15")
    private String firstName;
    @Column(nullable = false, length = 25)
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 15, message = "the length must be between 2 and 25")
    private String lastName;
    @Column(nullable = false, unique = true)
    @NotNull
    @Min(value = 1111111,message = "must be 7 digits ")
    @Digits(integer = 7, fraction = 0, message = "must be 7 digits")
    private Long personalId;
    @Column(nullable = false, unique = true)
    @NotNull
    @Min(value = 1111111111,message = "must be 10 digits")
    @Digits(integer = 10, fraction = 0, message = "must be 10 digits")
    private Long nationalId;


    @OneToMany(
            mappedBy = "professor",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Course> courses;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "college_id",
            referencedColumnName = "collegeId"
    )
    @JsonIgnore
    private College professorCollege;

    @ManyToMany(mappedBy = "professorsOfStudent",
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Student> studentsOfProfessor;

    public Professor() {
    }

    public Long getProfessorId() {
        return professorId;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public College getProfessorCollege() {
        return professorCollege;
    }

    public void setProfessorCollege(College college) {
        this.professorCollege = college;
    }

    public List<Student> getStudentsOfProfessor() {
        return studentsOfProfessor;
    }

    public void setStudentsOfProfessor(List<Student> studentsOfProfessor) {
        this.studentsOfProfessor = studentsOfProfessor;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "professorId=" + professorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalId=" + personalId +
                ", nationalId=" + nationalId +
                '}';
    }
}
