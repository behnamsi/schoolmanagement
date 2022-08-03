package com.behnam.school.management.service;

import com.behnam.school.management.dto.CollegeDTO;
import com.behnam.school.management.model.College;
import com.behnam.school.management.newDto.CollegeDto;
import com.behnam.school.management.repository.CollegeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollegeServiceUnitTest {

    @Mock
    CollegeRepository repository;
    @InjectMocks
    CollegeService service;

    College college1;
    College college2;
    College college3;
    CollegeDto collegeDto1;
    CollegeDto collegeDto2;
    CollegeDto collegeDto3;

    @BeforeEach
    void setUp() {
        service = new CollegeService(repository);
        college1 = new College();
        college2 = new College();
        college3 = new College();
        collegeDto1 = new CollegeDto("computer");
        collegeDto2 = new CollegeDto("electronic");
        collegeDto3 = new CollegeDto("math");
        college1.setCollegeId(1L);
        college2.setCollegeId(2L);
        college3.setCollegeId(3L);
        college1.setCollegeName("computer");
        college2.setCollegeName("electronic");
        college3.setCollegeName("math");

    }

    @Test
    void getAllColleges() {
        Pageable collegePageable = PageRequest.of(0, 3, Sort.by("collegeName").ascending());
        Page<College> collegeList = new PageImpl<>(List.of(college1, college2,college3));
        when(repository.findAll(collegePageable))
                .thenReturn(collegeList);
        List<CollegeDto> collegeDtoListExpected = new ArrayList<>(List.of(collegeDto1, collegeDto2, collegeDto3));
        List<CollegeDto> collegeDTOListActual = service.getAllColleges(1, 3);
        assertThat(collegeDTOListActual.size()).isEqualTo(collegeDtoListExpected.size());
        System.out.println("collegeDTOListActual = " + collegeDTOListActual);
        System.out.println("collegeDtoListExpected = " + collegeDtoListExpected);
        assertThat(collegeDTOListActual.get(0).getName()).isEqualTo(collegeDtoListExpected.get(0).getName());
    }

    @Test
    void addCollege() {
        College college = service.addCollege(collegeDto1);
        assertThat(college.getCollegeName()).isEqualTo("computer");
        assertThat(college).isInstanceOf(College.class);
    }

    @Test
    void deleteCollegeByCollegeName() {
        when(repository.existsCollegeByCollegeName(college1.getCollegeName()))
                .thenReturn(true);
        service.deleteCollegeByName(college1.getCollegeName());
    }

    @Test
    void updateCollege() {
        when(repository.findById(college1.getCollegeId()))
                .thenReturn(Optional.ofNullable(college1));
        service.updateCollege(1L, "comp science");
        assertThat(college1.getCollegeName()).isEqualTo("comp science");
    }

    // ----------------- NEW DTO TESTING -----------------

//    @Test
//    void shouldGetAllCollegesWithNewDto() {
//        Pageable collegePageable = PageRequest.of(0, 3, Sort.by("collegeName").ascending());
//        List<College> colleges=new ArrayList<>(List.of(college1, college2,college3));
//        Page<College> collegeList = new PageImpl<>(colleges);
//        when(repository.findAll(collegePageable))
//                .thenReturn(collegeList);
//
//        List<CollegeDto> collegeDtoListExpected = new ArrayList<>(List.of(collegeDto1, collegeDto2, collegeDto3));
//        List<CollegeDto> collegeDTOListActual = newService.getAllColleges(1, 3);
//        assertThat(collegeDTOListActual.size()).isEqualTo(collegeDtoListExpected.size());
//        System.out.println("collegeDTOListActual = " + collegeDTOListActual);
//        System.out.println("collegeDtoListExpected = " + collegeDtoListExpected);
//        assertThat(collegeDTOListActual.get(0).getName()).isEqualTo(collegeDtoListExpected.get(0).getName());
//    }

//    @Test
//    void shouldAddCollegeWithNewDto() {
//        College college = newService.addCollege(collegeDto1);
//        assertThat(college.getCollegeName()).isEqualTo("computer");
//    }


}