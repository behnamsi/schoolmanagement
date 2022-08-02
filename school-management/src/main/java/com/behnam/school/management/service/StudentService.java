package com.behnam.school.management.service;

import com.behnam.school.management.dto.StudentDTO;
import com.behnam.school.management.model.College;
import com.behnam.school.management.model.Student;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.model.Course;
import com.behnam.school.management.repository.CourseRepository;
import com.behnam.school.management.model.Professor;
import com.behnam.school.management.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository repository;

    private final CollegeRepository collegeRepository;

    private final CourseRepository courseRepository;

    @Autowired
    public StudentService(StudentRepository repository, CollegeRepository collegeRepository, CourseRepository courseRepository) {
        this.repository = repository;
        this.collegeRepository = collegeRepository;
        this.courseRepository = courseRepository;
    }

    public List<StudentDTO> getAllStudents(Integer limit, Integer page) {
        // limit and page filter
        if (limit == null) limit = 3;
        if (page == null || page == 0) page = 0;
        else page -= 1;
        if (limit > 100) throw new IllegalStateException("limit should not be more than 100");
        // paging nad sorting data
        Pageable studentPageable =
                PageRequest.of(page, limit, Sort.by("lastName").descending());
        Page<Student> studentPage = repository.findAll(studentPageable);
        // mapping data to dto
        List<StudentDTO> studentDTOS = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Student student :
                studentPage.getContent()) {
            StudentDTO studentDTO = new StudentDTO();
            modelMapper.map(student, studentDTO);
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }


    public Student addStudent(StudentDTO studentDTO, Long collegeId) {
        if (collegeId != null) {
            College college = collegeRepository.findById(collegeId).orElseThrow(() ->
                    new IllegalStateException("invalid college id"));
            // mapping to entity
            Student student = new Student();
            ModelMapper mapper = new ModelMapper();
            mapper.getConfiguration().setAmbiguityIgnored(true);
            mapper.map(studentDTO, student);
            Optional<Student> studentUniId = repository.findStudentByUniversityIdOptional(student.getUniversityId());
            Optional<Student> studentnatId = repository.findStudentByNationalId(student.getNationalId());
            if (studentUniId.isPresent()) {
                throw new IllegalStateException("university id is taken");
            }
            if (studentnatId.isPresent()) {
                throw new IllegalStateException("national id is taken");
            }

            student.setStudentCollege(college);
            repository.save(student);
            return student;
        } else {
            throw new IllegalStateException("college id can not be null");
        }
    }

//    @Transactional
//    public void deleteStudent(Long id) {
//        if (!repository.existsById(id)) {
//            throw new IllegalStateException("student with this Id does not exists.");
//        }
//        repository.deleteById(id);
//    }

    @Transactional
    public void deleteStudentByUniId(Long uniId) {
        if (!repository.existsByUniversityId(uniId)) {
            throw new IllegalStateException("invalid university id.");
        }
        repository.deleteStudentByUniversityId(uniId);
    }

    @Transactional
    public Student updateStudent(Long uniId, String first_name, String last_name, List<String> courses,
                                 Long nationalId) {
        if (!repository.existsByUniversityId(uniId)) {
            throw new IllegalStateException("invalid university id");
        }
        Student student = repository.findStudentByUniversityId(uniId);

        Optional<Student> studentByNationalId = repository.findStudentByNationalId(nationalId);
        if (studentByNationalId.isPresent()) {
            throw new IllegalStateException("national id has a owner");
        }

        if (first_name != null && first_name.length() > 0 &&
                !Objects.equals(student.getFirstName(), first_name)) {
            student.setFirstName(first_name);
        }
        if (last_name != null && last_name.length() > 0 &&
                !Objects.equals(student.getLastName(), last_name)) {
            student.setLastName(last_name);
        }
        if (!courses.isEmpty()) {
            List<Course> courses1 = new ArrayList<>();
            if (student.getStudentCourses() != null) courses1.addAll(student.getStudentCourses());
            List<Professor> professorsOfStudent = new ArrayList<>();
            for (String courseName : courses) {
                if (!courseRepository.existsCourseByCourseName(courseName)) {
                    throw new IllegalStateException("invalid course name");
                }
                Course course = courseRepository.findCourseByCourseName(courseName);
                professorsOfStudent.add(course.getProfessor());
                courses1.add(course);
                student.setStudentCourses(courses1);
                student.setProfessorsOfStudent(professorsOfStudent);
            }
        }
        if (nationalId != null &&
                !Objects.equals(student.getLastName(), last_name)) {
            student.setNationalId(nationalId);
        }
        return student;
    }

    @Transactional
    public List<String> getStudentCourses(Long uniId) {
        if (!repository.existsByUniversityId(uniId)) {
            throw new IllegalStateException("invalid uni id for the student");
        }
        Student student = repository.findStudentByUniversityId(uniId);
        List<String> courses = new ArrayList<>();
        for (Course course : student.getStudentCourses()) {
            courses.add(course.getCourseName());
        }
        return courses;
    }

    @Transactional
    public void addScoreCourse(Long uniId, String courseName, Double score) {

        if (!repository.existsByUniversityId(uniId)) {
            throw new IllegalStateException("invalid university id");
        }

        if (!courseRepository.existsCourseByCourseName(courseName)) {
            throw new IllegalStateException("invalid course name");
        }


        Student student = repository.findStudentByUniversityId(uniId);
        List<Course> courseList = student.getStudentCourses();
        boolean courseFlag = false;
        for (Course course : courseList) {
            if (course.getCourseName().equals(courseName)) {
                courseFlag = true;
                break;
            }
        }
        if (!courseFlag) {
            throw new IllegalStateException("course not defined for this student");
        }

        Map<String, Double> scoreCourse = student.getScores();
        scoreCourse.put(courseName, score);
        student.setScores(scoreCourse);
    }

    // delete a course of a student
    @Transactional
    public void deleteStudentCourse(Long uniId, String courseName) {
        //TODO drop the professor of that course.
        if (!repository.existsByUniversityId(uniId)) {
            throw new IllegalStateException("invalid university id");
        }
        if (!courseRepository.existsCourseByCourseName(courseName)) {
            throw new IllegalStateException("invalid course name");
        }
        Student student = repository.findStudentByUniversityId(uniId);
        boolean courseFlag = false;
        List<Course> courseList = new LinkedList<>(student.getStudentCourses());
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCourseName().equals(courseName)) {
                courseFlag = true;
                courseList.remove(i);
                break;
            }
        }
        if (!courseFlag) {
            throw new IllegalStateException("this student does not have this course");
        }
        student.setStudentCourses(courseList);
        Map<String, Double> studentScoresMap = student.getScores();
        for (String courseName1 : studentScoresMap.keySet()) {
            if (courseName1.equals(courseName)) {
                studentScoresMap.remove(courseName);
                break;
            }
        }
        student.setScores(studentScoresMap);
    }

    @Transactional
    public Double getStudentAverage(Long uniID) {
        if (!repository.existsByUniversityId(uniID)) {
            throw new IllegalStateException("invalid uni id");
        }
        Student student = repository.findStudentByUniversityId(uniID);

        List<Course> courses = new ArrayList<>(student.getStudentCourses());
        Map<String, Double> scores = new HashMap<>(student.getScores());
        if (courses.size() == 0) {
            throw new IllegalStateException("no courses taken");
        }
        if (courses.size() > scores.size()) {
            throw new IllegalStateException("all course`s results must be present.");
        }
        int numOfUnits = 0;
        double sum = 0, result;
        for (Course course : courses) {
            sum += scores.get(course.getCourseName()) * course.getUnitNumber();
            numOfUnits += course.getUnitNumber();
        }
        result = sum / numOfUnits;
        return result;
    }

    public StudentDTO getStudent(Long studentUniId) {
        if (!repository.existsByUniversityId(studentUniId)) {
            throw new IllegalStateException("invalid uni id");
        }
        Student student = repository.findStudentByUniversityId(studentUniId);
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(student, studentDTO);
        return studentDTO;
    }
}
