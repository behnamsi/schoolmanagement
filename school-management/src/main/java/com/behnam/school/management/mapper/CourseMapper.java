package com.behnam.school.management.mapper;

import com.behnam.school.management.model.Course;
import com.behnam.school.management.dto.CourseDto;

public class CourseMapper {
    public CourseDto courseToDto(Course course) {
        String name = course.getCourseName();
        int unitNumber = course.getUnitNumber();
        String professor = String.format("%s %s",
                course.getProfessor().getFirstName(),
                course.getProfessor().getLastName());
        String collegeName=course.getCourseCollege().getCollegeName();
        return new CourseDto(name, unitNumber, professor,collegeName);
    }

    public Course dtoToCourse(CourseDto courseDto){
        Course course=new Course();
        course.setCourseName(courseDto.getName());
        course.setUnitNumber(courseDto.getUnitNumber());
        return course;
    }
}
