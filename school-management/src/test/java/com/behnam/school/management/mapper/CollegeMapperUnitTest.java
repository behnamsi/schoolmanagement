package com.behnam.school.management.mapper;

import com.behnam.school.management.model.College;
import com.behnam.school.management.newDto.CollegeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CollegeMapperUnitTest {

    CollegeMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CollegeMapper();
    }

    @Test
    void shouldMapEntityToDto() {
        College college = new College();
        college.setCollegeName("computer");
        college.setCollegeId(1L);
         CollegeDto collegeDto= mapper.toCollegeDto(college);
        System.out.println("collegeDto = " + collegeDto);
    }

}