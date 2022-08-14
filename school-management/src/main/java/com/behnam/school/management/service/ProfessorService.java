package com.behnam.school.management.service;

import com.behnam.school.management.mapper.ProfessorMapper;
import com.behnam.school.management.model.College;
import com.behnam.school.management.model.Professor;
import com.behnam.school.management.dto.ProfessorDto;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.model.Course;
import com.behnam.school.management.repository.ProfessorRepository;
import com.behnam.school.management.model.Student;
import com.behnam.school.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;
    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;

    @Autowired
    public ProfessorService(ProfessorRepository repository, StudentRepository studentRepository, CollegeRepository collegeRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    public List<ProfessorDto> getAllProfessors(Integer page, Integer limit) {
        // limit and paging filter
        if (limit == null) limit = 3;
        if (page == null) page = 0;
        else page -= 1;
        if (limit > 100) throw new IllegalStateException("limit can not be more than 100");
        // paging and sorting data
        Pageable professorPageable = PageRequest.of(page, limit, Sort.by("lastName").ascending());
        Page<Professor> professorPage = repository.findAll(professorPageable);
        // mapping to dto
        ProfessorMapper mapper = new ProfessorMapper();
        return professorPage
                .getContent()
                .stream()
                .map(mapper::professorToDto)
                .collect(toList());
    }

    public void addProfessor(ProfessorDto professorDto, Long collegeId) {
        if (collegeId != null) {
            College college = collegeRepository.findById(collegeId).orElseThrow(() ->
                    new IllegalStateException("invalid college id"));
            // mapping to entity
            Professor professor;
            ProfessorMapper mapper = new ProfessorMapper();
            professor = mapper.dtoTOProfessor(professorDto);
            // check for national and personal id
            Optional<Professor> professorNationalId = repository.findProfessorByNationalId
                    (professor.getNationalId());
            Optional<Professor> professorPersonalId = repository.findProfessorByPersonalId
                    (professor.getPersonalId());
            if (professorNationalId.isPresent() || professorPersonalId.isPresent()) {
                throw new IllegalStateException("personal id or national id is taken");
            }
            professor.setProfessorCollege(college);
            repository.save(professor);
        }
    }

    public void deleteProfessor(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("invalid id to delete professor.");
        }
        repository.deleteById(id);
    }

    @Transactional
    public void updateProfessor(Long id, String first_name,
                                String last_name, Long nationalId, Long personalId) {
        Professor professor = repository.findById(id).orElseThrow(() -> new
                IllegalStateException("invalid id to update professor."));

        Optional<Professor> professorByNationalId = repository.findProfessorByNationalId(nationalId);
        Optional<Professor> professorByPersonalId = repository.findProfessorByPersonalId(personalId);
        Optional<Student> studentByNationalId = studentRepository.findStudentByNationalId(nationalId);
        if (professorByNationalId.isPresent() || studentByNationalId.isPresent()) {
            throw new IllegalStateException("national id has owner");
        }
        if (professorByPersonalId.isPresent()) {
            throw new IllegalStateException("personal id is taken.");
        }
        if (first_name != null && first_name.length() > 0 &&
                !Objects.equals(professor.getFirstName(), first_name)) {
            professor.setFirstName(first_name);
        }

        if (last_name != null && last_name.length() > 0 &&
                !Objects.equals(professor.getLastName(), last_name)) {
            professor.setLastName(last_name);
        }
        if (nationalId != null && !nationalId.equals(professor.getNationalId())) {
            professor.setNationalId(nationalId);
        }
        if (personalId != null && !personalId.equals(professor.getPersonalId())) {
            professor.setPersonalId(personalId);
        }
    }

    @Transactional
    public List<String> getProfessorStudents(Long professorId) {
        Professor professor = repository.findById(professorId).orElseThrow(
                () -> new IllegalStateException("invalid professor id")
        );
        List<String> studentsName = new ArrayList<>();
        for (Student student : professor.getStudentsOfProfessor()) {
            studentsName.add(student.getFirstName() + " " + student.getLastName());
        }
        return studentsName;
    }

    @Transactional
    public List<String> getProfessorStudentsAverages(Long professorId) {
        Professor professor = repository.findById(professorId).orElseThrow(
                () -> new IllegalStateException("invalid professor id")
        );
        List<String> studentsAverageList = new ArrayList<>();
        for (Student student : professor.getStudentsOfProfessor()) {
            List<Course> courses = student.getStudentCourses();
            Map<String, Double> scores = student.getScores();
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
            studentsAverageList.add(student.getFirstName() + " " + student.getLastName()
                    + " : " + result);
        }
        return studentsAverageList;
    }

    @Transactional
    public List<String> getProfessorsCourses(Long professorId) {

        Professor professor = repository.findById(professorId).orElseThrow(
                () -> new IllegalStateException("invalid professor id")
        );
        List<String> professorCourses = new ArrayList<>();
        for (Course course : professor.getCourses()) {
            professorCourses.add(course.getCourseName());
        }
        return professorCourses;

    }

    @Transactional
    public List<String> getProfessorStudentsByCourse(Long professorId, String courseName) {

        Professor professor = repository.findById(professorId).orElseThrow(
                () -> new IllegalStateException("invalid professor id")
        );
        boolean courseExists = false;
        List<Student> students = new ArrayList<>();
        List<String> studentsNames = new ArrayList<>();
        for (Course course : professor.getCourses()) {
            if (course.getCourseName().equals(courseName)) {
                courseExists = true;
                students.addAll(course.getEnrolled_students());
                break;
            }
        }
        if (!courseExists) {
            throw new IllegalStateException("invalid course");
        }
        for (Student student : students) {
            studentsNames.add(student.getFirstName() + " " + student.getLastName());
        }
        return studentsNames;

    }

    @Transactional
    public List<String> getProfessorStudentsAverageByCourse(Long professorId, String courseName) {
        Professor professor = repository.findById(professorId).orElseThrow(
                () -> new IllegalStateException("invalid professor id")
        );
        boolean courseExists = false;
        List<Student> students = new ArrayList<>();
        for (Course course : professor.getCourses()) {
            if (course.getCourseName().equals(courseName)) {
                courseExists = true;
                students.addAll(course.getEnrolled_students());
                break;
            }
        }
        if (!courseExists) {
            throw new IllegalStateException("invalid course");
        }
        List<String> studentAverages = new ArrayList<>();
        for (Student student : students) {
            List<Course> courses = student.getStudentCourses();
            Map<String, Double> scores = student.getScores();
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
            studentAverages.add(student.getFirstName() + " " + student.getLastName() + " : " +
                    result);
        }
        return studentAverages;
    }

    public ProfessorDto getProfessor(Long profId) {
        Professor professor = repository.findById(profId).orElseThrow(
                () -> new IllegalStateException("invalid professor id")
        );
        ProfessorMapper mapper = new ProfessorMapper();
        return mapper.professorToDto(professor);
    }
}
