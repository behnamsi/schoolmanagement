package com.behnam.school.management.mapper;

import com.behnam.school.management.model.College;
import com.behnam.school.management.model.Course;
import com.behnam.school.management.newDto.CollegeDto;
import java.util.List;
import static java.util.stream.Collectors.toList;


public class CollegeMapper {
    public CollegeDto toCollegeDto(College college) {
        String name = college.getCollegeName();
        if (college.getCoursesInCollege() != null) {
            List<String> courses = college
                    .getCoursesInCollege()
                    .stream()
                    .map(Course::getCourseName)
                    .collect(toList());
            return new CollegeDto(name, courses);
        }
        return new CollegeDto(name);
    }

    public College toCollege(CollegeDto collegeDto) {
        College college = new College();
        college.setCollegeName(collegeDto.getName());
        return college;
    }
}
