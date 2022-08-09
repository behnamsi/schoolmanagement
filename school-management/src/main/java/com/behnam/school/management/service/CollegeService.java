package com.behnam.school.management.service;


import com.behnam.school.management.mapper.CollegeMapper;
import com.behnam.school.management.model.College;
import com.behnam.school.management.dto.CollegeDto;
import com.behnam.school.management.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class CollegeService {


    private final CollegeRepository repository;

    @Autowired
    public CollegeService(CollegeRepository repository) {
        this.repository = repository;
    }

    public List<CollegeDto> getAllColleges(Integer page, Integer limit) {
        CollegeMapper mapper = new CollegeMapper();
        // page and limit filters
        if (limit == null) limit = 3;
        if (page == null) page = 0;
        else page -= 1;
        if (limit > 100) throw new IllegalStateException("limit can not be more than 100");
        // paging and sorting and getting the data
        Pageable collegePageable = PageRequest.of(page, limit, Sort.by("collegeName").ascending());
        Page<College> collegeList = repository.findAll(collegePageable);
        return collegeList
                .getContent()
                .stream()
                .map(mapper::toCollegeDto)
                .toList();
    }

    public College addCollege(CollegeDto collegeDto) {
        CollegeMapper mapper = new CollegeMapper();
        College college = mapper.toCollege(collegeDto);
        repository.save(college);
        return college;
    }

    @Transactional
    public void deleteCollegeByID(Long collegeId) {
        if (!repository.existsById(collegeId)) {
            throw new IllegalStateException("this college does not exists to delete.");
        }
        repository.deleteById(collegeId);
    }

    @Transactional
    public void deleteCollegeByName(String collegeName) {
        if (!repository.existsCollegeByCollegeName(collegeName)) {
            throw new IllegalStateException("this college does not exists to delete.");
        }
        repository.deleteCollegeByCollegeName(collegeName);
    }

    @Transactional
    public void updateCollege(Long collegeId, String collegeName) {
        College college = repository.findById(collegeId).orElseThrow(() ->
                new IllegalStateException("this college does not exists to update"));
        if (collegeName != null && collegeName.length() > 0
                && !Objects.equals(collegeName, college.getCollegeName())) {
            college.setCollegeName(collegeName);
        }
    }
}
