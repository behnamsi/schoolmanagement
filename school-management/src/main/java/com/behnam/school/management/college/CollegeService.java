package com.behnam.school.management.college;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CollegeService {


    private final CollegeRepository repository;

    @Autowired
    public CollegeService(CollegeRepository repository) {
        this.repository = repository;
    }

    public List<CollegeDTO> getAllColleges(Integer page, Integer limit) {
        if (limit == null) limit = 3;
        if (page == null || page == 0) page = 0;
        else page -= 1;
        if (limit > 100) throw new IllegalStateException("limit can not be more than 100");
        Pageable collegePageable = PageRequest.of(page, limit, Sort.by("collegeName").descending());
        Page<College> collegePage = repository.findAll(collegePageable);
        if (collegePage.isEmpty()) throw new IllegalStateException("this Entity has " +
                collegePage.getTotalPages() + " pages with "
                + collegePage.getTotalElements() + " Elements");
        List<CollegeDTO> collegeDTOS = new ArrayList<>();
        for (College college :
                collegePage.getContent()) {
            CollegeDTO collegeDTO = new CollegeDTO();
            BeanUtils.copyProperties(college, collegeDTO);
            collegeDTOS.add(collegeDTO);
        }
        return collegeDTOS;
//        return collegePage.getContent();
//        return repository.findAll();
    }

    public void addCollege(CollegeDTO collegeDTO) {
        College college = new College();
        BeanUtils.copyProperties(collegeDTO, college);
        repository.save(college);
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
