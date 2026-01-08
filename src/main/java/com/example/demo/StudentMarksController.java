package com.example.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/marks")
@CrossOrigin("*")
public class StudentMarksController {

    @Autowired
    private StudentMarksService service;

    // CREATE
    @PostMapping("/save")
    public StudentMarks saveMarks(@RequestBody StudentMarks marks) {
        return service.saveMarks(marks);
    }

    // READ ALL
    @GetMapping("/all")
    public List<StudentMarks> getAllMarks() {
        return service.getAllMarks();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public StudentMarks getMarksById(@PathVariable Long id) {
        return service.getMarksById(id);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteMarks(@PathVariable Long id) {
        service.deleteMarks(id);
        return "Marks record deleted successfully";
    }
}
