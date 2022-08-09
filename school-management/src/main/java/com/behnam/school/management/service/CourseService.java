package com.behnam.school.management.service;

import com.behnam.school.management.mapper.CourseMapper;
import com.behnam.school.management.model.College;
import com.behnam.school.management.model.Course;
import com.behnam.school.management.dto.CourseDto;
import com.behnam.school.management.repository.CollegeRepository;
import com.behnam.school.management.model.Professor;
import com.behnam.school.management.repository.ProfessorRepository;
import com.behnam.school.management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final ProfessorRepository professorRepository;
    private final CollegeRepository collegeRepository;

    @Autowired
    public CourseService(CourseRepository repository, ProfessorRepository professorRepository, CollegeRepository collegeRepository) {
        this.repository = repository;
        this.professorRepository = professorRepository;
        this.collegeRepository = collegeRepository;
    }

    public List<CourseDto> getAllCourses(Integer page, Integer limit) {
        CourseMapper mapper=new CourseMapper();
        // limit and paging filters
        if (limit == null) limit = 3;
        if (page == null) page = 0;
        else page -= 1;
        if (limit > 100) throw new IllegalStateException("limit can not be more than 100");
        // paging and sorting data
        Pageable coursePageable = PageRequest.of(page, limit, Sort.by("courseName").ascending());
        Page<Course> coursePage = repository.findAll(coursePageable);
        return coursePage
                .getContent()
                .stream()
                .map(mapper::courseToDto)
                .collect(toList());
    }

    public void addCourse(CourseDto courseDto, Long professorId, Long collegeId) {
        if (professorId != null && collegeId != null) {
            Professor professor = professorRepository.findById(professorId).
                    orElseThrow(() -> new IllegalStateException("invalid professor id"));
            College college = collegeRepository.findById(collegeId).orElseThrow(() ->
                    new IllegalStateException("invalid college id"));
            // mapping to entity
            CourseMapper mapper = new CourseMapper();
            Course course;
            course = mapper.dtoToCourse(courseDto);
            course.setProfessor(professor);
            course.setCourseCollege(college);
            repository.save(course);
        } else {
            throw new IllegalStateException("could not add a course without college and professor");
        }

    }

    @Transactional
    public void deleteCourseByName(String courseName) {
        if (!repository.existsCourseByCourseName(courseName)) {
            throw new IllegalStateException("this course name is invalid");
        }
        repository.deleteCourseByCourseName(courseName);
    }

//    @Transactional
//    public void deleteCourseById(Long courseId) {
//        if (!repository.existsById(courseId)) {
//            throw new IllegalStateException("this course id is invalid");
//        }
//        repository.deleteById(courseId);
//    }

    @Transactional
    public void updateCourse(Long courseId, String courseName, Integer unitNumber, Long professorId) {
        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new IllegalStateException("invalid professor to add into course"));
        Course course = repository.findById(courseId).orElseThrow(() -> new IllegalStateException("invalid" +
                "course id to update"));
        if (courseName != null && !Objects.equals(courseName, course.getCourseName())
                && courseName.length() > 0) {
            course.setCourseName(courseName);
        }
        if (unitNumber != null && !Objects.equals(unitNumber, course.getUnitNumber())
                && unitNumber > 0) {
            course.setUnitNumber(unitNumber);
        }
        if (course.getProfessor() != null) {
            if (!Objects.equals(professorId, course.getProfessor().getProfessorId())
                    && professorId > 0) {
                course.setProfessor(professor);
            }
        } else {
            course.setProfessor(professor);
        }

    }
}
