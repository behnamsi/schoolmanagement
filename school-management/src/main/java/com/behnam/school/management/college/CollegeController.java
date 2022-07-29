package com.behnam.school.management.college;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ) {
        return service.getAllColleges(page,limit);
    }

    @PostMapping("add/")
    public void addCollege(@RequestBody CollegeDTO college) {
        service.addCollege(college);
    }

    @DeleteMapping(path = "{collegeId}/delete-id")
    public void deleteCollegeByID(@PathVariable("collegeId") Long collegeId) {
        service.deleteCollegeByID(collegeId);
    }

    @DeleteMapping(path = "{collegeName}/delete-name")
    public void deleteCollegeByName(@PathVariable("collegeName") String collegeName) {
        service.deleteCollegeByName(collegeName);
    }

    @PutMapping(path = "{collegeId}/update")
    public void updateCollege(
            @PathVariable("collegeId") Long collegeId,
            @RequestParam(required = false) String collegeName
    ) {
        service.updateCollege(collegeId, collegeName);
    }
}
