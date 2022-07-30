package com.behnam.school.management.course;

import com.behnam.school.management.college.College;
import com.behnam.school.management.professor.Professor;
import com.behnam.school.management.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table
public class Course {
    @Id

    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(updatable = false)
    private Long courseId;
    @Column(nullable = false, length = 20, unique = true)
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 20)
    private String courseName;
    @Column(nullable = false)
    @Min(1) @Max(3)
    @NotNull
    private Integer unitNumber;


    @ManyToOne(
            cascade = CascadeType.MERGE,
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "professor_id",
            referencedColumnName = "professorId"
    )
    @JsonIgnore
    private Professor professor;


    @ManyToOne(
            cascade = CascadeType.MERGE,
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "college_id",
            referencedColumnName = "collegeId"
    )
    @JsonIgnore
    private College courseCollege;

    @ManyToMany(
            mappedBy = "studentCourses",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Student> enrolled_students;

    public Course() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public College getCourseCollege() {
        return courseCollege;
    }

    public void setCourseCollege(College college) {
        this.courseCollege = college;
    }

    public List<Student> getEnrolled_students() {
        return enrolled_students;
    }

    public void setEnrolled_students(List<Student> enrolled_students) {
        this.enrolled_students = enrolled_students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", unitNumber=" + unitNumber +
                '}';
    }
}
