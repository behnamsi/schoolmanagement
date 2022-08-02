package com.behnam.school.management.service;

import com.behnam.school.management.dto.CollegeDTO;
import com.behnam.school.management.model.College;
import com.behnam.school.management.repository.CollegeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CollegeServiceUnitTest {

    @Mock
    CollegeRepository repository;
    @InjectMocks
    CollegeService service;

    College college;

    @BeforeEach
    void setUp() {
        service = new CollegeService(repository);
        college = new College();
        college.setCollegeId(1L);
        college.setCollegeName("computer");
    }

    @Test
    void getAllColleges() {
        Pageable pageable =
                PageRequest.of(0, 3,
                        Sort.by("collegeName").descending());

        Page<College> collegePage = new PageImpl<>(List.of(college));
        when(repository.findAll(pageable)).thenReturn(collegePage);
        List<CollegeDTO> collegeDTOList = service.getAllColleges(
                null,
                null);
        assertThat(collegeDTOList.get(0).getCollegeName()).isEqualTo("computer");
    }

    @Test
    void addCollege() {
        CollegeDTO collegeDTO = new CollegeDTO();
        BeanUtils.copyProperties(college, collegeDTO);
        College savedCollege = service.addCollege(collegeDTO);
        savedCollege.setCollegeId(1L);
        assertThat(savedCollege).isNotNull();
        assertThat(college.getCollegeName()).isEqualTo(savedCollege.getCollegeName());
    }

    @Test
    void deleteCollegeByCollegeName() {
        when(repository.existsCollegeByCollegeName(college.getCollegeName()))
                .thenReturn(true);
        service.deleteCollegeByName(college.getCollegeName());
    }

    @Test
    void updateCollege() {
        when(repository.findById(college.getCollegeId()))
                .thenReturn(Optional.ofNullable(college));
        service.updateCollege(1L,"comp science");
        assertThat(college.getCollegeName()).isEqualTo("comp science");
    }
}