package com.behnam.school.management.college;

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
    public List<CollegeDTO> getAllColleges(
            @RequestParam(required = false) @Min(1) Integer page,
            @RequestParam(required = false) @Min(1) Integer limit
    ) {
        return service.getAllColleges(page, limit);
    }

    @PostMapping("add/")
    public void addCollege(@Valid @RequestBody CollegeDTO college) {
        service.addCollege(college);
    }

    @DeleteMapping(path = "{collegeId}/delete-id")
    public void deleteCollegeByID(
            @PathVariable("collegeId") @Min(1) Long collegeId) {
        service.deleteCollegeByID(collegeId);
    }

    @DeleteMapping(path = "{collegeName}/delete-name")
    public void deleteCollegeByName(
            @PathVariable("collegeName") @NotEmpty @Size(min = 1, max = 20) String collegeName) {
        service.deleteCollegeByName(collegeName);
    }

    @PutMapping(path = "{collegeId}/update")
    public void updateCollege(
            @PathVariable("collegeId") @Min(1) Long collegeId,
            @RequestParam(required = false) @NotEmpty @Size(min = 1, max = 20) String collegeName
    ) {
        service.updateCollege(collegeId, collegeName);
    }
}
