package com.behnam.school.management.controller;


import com.behnam.school.management.dto.CollegeDto;
import com.behnam.school.management.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(path = "api/colleges")
public class CollegeController {
    private final CollegeService service;


    @Autowired
    public CollegeController(CollegeService service) {
        this.service = service;
    }

    @GetMapping
    public List<CollegeDto> getAllColleges(
            @RequestParam(required = false) @Min(1) Integer page,
            @RequestParam(required = false) @Min(1) Integer limit
    ) {
        return service.getAllColleges(page, limit);
    }

    @PostMapping("")
    public void addCollege(@Valid @RequestBody CollegeDto collegeDto) {
        service.addCollege(collegeDto);
    }

    @DeleteMapping(path = "{collegeId}/delete-id")
    public void deleteCollegeByID(
            @PathVariable("collegeId") @Min(1) Long collegeId) {
        service.deleteCollegeByID(collegeId);
    }

    @DeleteMapping(path = "{collegeName}")
    public void deleteCollegeByName(
            @PathVariable("collegeName") @NotEmpty @Size(min = 1, max = 20) String collegeName) {
        service.deleteCollegeByName(collegeName);
    }

    @PutMapping(path = "{collegeId}")
    public void updateCollege(
            @PathVariable("collegeId") @Min(1) Long collegeId,
            @RequestParam(required = false) @NotEmpty @Size(min = 1, max = 20) String collegeName
    ) {
        service.updateCollege(collegeId, collegeName);
    }
}
