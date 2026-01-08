package com.example.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentMarksService {

    @Autowired
    private StudentMarksRepository repository;

    // CREATE
    public StudentMarks saveMarks(StudentMarks marks) {
        return repository.save(marks);
    }

    // READ ALL
    public List<StudentMarks> getAllMarks() {
        return repository.findAll();
    }

    // READ BY ID
    public StudentMarks getMarksById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }

    // DELETE
    public void deleteMarks(Long id) {
        repository.deleteById(id);
    }
}

