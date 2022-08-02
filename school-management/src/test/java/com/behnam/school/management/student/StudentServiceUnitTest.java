package com.behnam.school.management.student;

import com.behnam.school.management.dto.StudentDTO;
import com.behnam.school.management.model.College;
import com.behnam.school.management.model.Course;
import com.behnam.school.management.model.Student;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.repository.CourseRepository;
import com.behnam.school.management.repository.StudentRepository;
import com.behnam.school.management.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;

import javax.swing.text.html.Option;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceUnitTest {


    @Mock
    StudentRepository studentRepository;
    @Mock
    CollegeRepository collegeRepository;
    @Mock
    CourseRepository courseRepository;
    @InjectMocks
    StudentService service;

    Student student1;
    Student student2;
    Student student3;
    College college;
    Course course1;
    Course course2;

    @BeforeEach
    void setUp() {
        service = new StudentService(
                studentRepository,
                collegeRepository,
                courseRepository);
        student1 = new Student();
        student2 = new Student();
        student3 = new Student();
        student1.setStudentId(1L);
        student2.setStudentId(2L);
        student3.setStudentId(3L);
        student1.setFirstName("Behnam1");
        student2.setFirstName("Behnam2");
        student3.setFirstName("Behnam3");
        student1.setLastName("Saqari1");
        student2.setLastName("Saqari2");
        student3.setLastName("Saqari3");
        student1.setNationalId(1234567890L);
        student2.setNationalId(1234567891L);
        student3.setNationalId(1234567892L);
        student1.setUniversityId(1234567L);
        student2.setUniversityId(1234568L);
        student3.setUniversityId(1234569L);
        college = new College();
        college.setCollegeId(1L);
        college.setCollegeName("computer");
        course1 = new Course();
        course2 = new Course();
        course1.setCourseId(1L);
        course1.setCourseId(2L);
        course1.setCourseName("java");
        course2.setCourseName("python");
        course1.setUnitNumber(3);
        course2.setUnitNumber(3);
    }

    @Test
    void shouldGetStudent() {
        when(studentRepository.existsByUniversityId(student1.getStudentId()))
                .thenReturn(true);
        when(studentRepository.findStudentByUniversityId(student1.getStudentId()))
                .thenReturn(student1);
        StudentDTO studentDTO = new StudentDTO();
        ModelMapper mapper = new ModelMapper();
        mapper.map(student1, studentDTO);
        StudentDTO studentDTO1 = service.getStudent(student1.getStudentId());
        System.err.println("studentDTO1 = " + studentDTO1);
        Assertions.assertThat(studentDTO1).isNotNull();
        assertThat(studentDTO1.getFirstName()).isEqualTo("Behnam1");
    }

    @Test
    void shouldGetAllStudents() {
        Pageable pageable =
                PageRequest.of(0, 3, Sort.by("lastName").descending());
        List<Student> studentList = new ArrayList<>(
                Arrays.asList(student1, student2, student3));
        Page<Student> studentPage = new PageImpl<>(studentList);
        when(studentRepository.findAll(pageable)).thenReturn(studentPage);
        List<StudentDTO> expectedStudentDTOS = service.getAllStudents(null, null);
        System.err.println("studentDTOS = " + expectedStudentDTOS);
        List<StudentDTO> actual = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();
        for (Student student :
                studentList) {
            StudentDTO studentDTO = new StudentDTO();
            mapper.map(student, studentDTO);
            actual.add(studentDTO);
        }
        assertThat(expectedStudentDTOS).isNotNull();
        assertThat(expectedStudentDTOS.size()).isEqualTo(3);
        assertThat(expectedStudentDTOS.get(0).getFirstName())
                .isEqualTo(actual.get(0).getFirstName());
    }

    @Test
    void shouldAddStudent() {
        when(collegeRepository.findById(college.getCollegeId()))
                .thenReturn(Optional.ofNullable(college));
        when(studentRepository.
                findStudentByUniversityIdOptional(student1.getUniversityId()))
                .thenReturn(Optional.empty());
        when(studentRepository.
                findStudentByNationalId(student1.getNationalId()))
                .thenReturn(Optional.empty());
        StudentDTO studentDTO = new StudentDTO();
        ModelMapper mapper = new ModelMapper();
        mapper.map(student1, studentDTO);
        Student savedStudent = service
                .addStudent(studentDTO, college.getCollegeId());
        savedStudent.setStudentId(student1.getStudentId());
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getFirstName()).isEqualTo("Behnam1");
        assertThat(student1.getNationalId())
                .isEqualTo(savedStudent.getNationalId());
    }

    @Test
    void shouldUpdateStudent() {
        List<Course> courseList = new ArrayList<>(List.of(course1));
        List<String> courseNameList = new ArrayList<>(List.of(course1.getCourseName()));
        when(studentRepository.existsByUniversityId(student1.getUniversityId()))
                .thenReturn(true);
        when(studentRepository.findStudentByUniversityId(
                student1.getUniversityId()))
                .thenReturn(student1);
        when(studentRepository.findStudentByNationalId(
                student1.getNationalId()))
                .thenReturn(Optional.empty());
        for (Course course : courseList) {
            when(courseRepository
                    .existsCourseByCourseName(course.getCourseName()))
                    .thenReturn(true);
        }
        when(courseRepository.findCourseByCourseName(course1.getCourseName()))
                .thenReturn(course1);
        System.out.println("student1 = " + student1);
        System.out.println("student1.getStudentCourses() = " + student1.getStudentCourses());

        Student changedStudentExpected = service.updateStudent(
                student1.getUniversityId(),
                "Sepehr",
                student1.getLastName(),
                courseNameList,
                student1.getNationalId()
        );

        System.err.println("changedStudentExpected = " + changedStudentExpected);
        System.out.println("student1.getStudentCourses() = " + student1.getStudentCourses());
        assertThat(changedStudentExpected.getFirstName()).isEqualTo("Sepehr");
    }

    @Test
    void shouldDeleteStudent() {
        when(studentRepository.existsByUniversityId(
                student1.getUniversityId()))
                .thenReturn(true);
        service.deleteStudentByUniId(student1.getUniversityId());
    }

    @Test
    void shouldGetStudentCourses() {
        student1.setStudentCourses(List.of(course1));
        when(studentRepository
                .existsByUniversityId(student1.getUniversityId()))
                .thenReturn(true);
        when(studentRepository
                .findStudentByUniversityId(student1.getUniversityId()))
                .thenReturn(student1);
        List<String> courseNameList = service.getStudentCourses(
                student1.getUniversityId());
        assertThat(courseNameList).isNotNull();
        assertThat(courseNameList.get(0)).isEqualTo("java");
    }

    @Test
    void shouldAddScoreCourse() {
        student1.setStudentCourses(List.of(course1));
        when(studentRepository
                .existsByUniversityId(student1.getUniversityId()))
                .thenReturn(true);
        when(courseRepository.existsCourseByCourseName(
                course1.getCourseName()))
                .thenReturn(true);
        when(studentRepository
                .findStudentByUniversityId(student1.getUniversityId()))
                .thenReturn(student1);
        service.addScoreCourse(
                student1.getUniversityId(),
                "java",
                19.5
        );
        Map<String, Double> expectedScore = new HashMap<>();
        expectedScore.put("java", 19.5);
        assertThat(student1.getScores()).isNotNull();
        assertThat(student1.getScores()).isEqualTo(expectedScore);
    }

    @Test
    void shouldDeleteStudentCourse() {
        student1.setStudentCourses(List.of(course1));
        List<Course> courseListExpected = new ArrayList<>();
        assertThat(student1.getStudentCourses()).isEqualTo(List.of(course1));
        when(studentRepository
                .existsByUniversityId(student1.getUniversityId()))
                .thenReturn(true);
        when(courseRepository.existsCourseByCourseName(
                course1.getCourseName()))
                .thenReturn(true);
        when(studentRepository
                .findStudentByUniversityId(student1.getUniversityId()))
                .thenReturn(student1);
        service.deleteStudentCourse(
                student1.getUniversityId(),
                "java");
        assertThat(student1.getStudentCourses()).isEqualTo(List.of());
        assertThat(student1.getStudentCourses()).isEqualTo(courseListExpected);
    }

    @Test
    void shouldGetStudentAverage() {
        student1.setStudentCourses(List.of(course1,course2));
        Map<String,Double> score=new HashMap<>();
        score.put("java",19.25);
        score.put("python",18.5);
        student1.setScores(score);
        when(studentRepository
                .existsByUniversityId(student1.getUniversityId()))
                .thenReturn(true);
        when(studentRepository
                .findStudentByUniversityId(student1.getUniversityId()))
                .thenReturn(student1);
        Double average = service.getStudentAverage(student1.getUniversityId());
        assertThat(average).isEqualTo(18.875);
    }

}
