package com.example.demo;


import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentMarksService {

    @Autowired
    private StudentMarksRepository repository;

    // CREATE - Check for duplicates before saving
    public StudentMarks saveMarks(StudentMarks marks) {
        // Check if hall ticket number already exists
        Optional<StudentMarks> existing = repository.findByHallTicketNumber(marks.getHallTicketNumber());
        if (existing.isPresent()) {
            // Update existing record instead of creating duplicate
            StudentMarks existingRecord = existing.get();
            existingRecord.setSetNumber(marks.getSetNumber());
            existingRecord.setMarks(marks.getMarks());
            return repository.save(existingRecord);
        }
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
    
    // REMOVE DUPLICATES - Keep only the latest record for each hall ticket
    public Map<String, Object> removeDuplicates() {
        List<StudentMarks> allRecords = repository.findAll();
        Map<String, StudentMarks> uniqueRecords = new HashMap<>();
        
        // Group by hall ticket number and keep the one with highest ID (latest)
        for (StudentMarks record : allRecords) {
            String hallTicket = record.getHallTicketNumber();
            if (!uniqueRecords.containsKey(hallTicket) || 
                record.getId() > uniqueRecords.get(hallTicket).getId()) {
                uniqueRecords.put(hallTicket, record);
            }
        }
        
        // Find duplicates to delete
        List<StudentMarks> toDelete = allRecords.stream()
            .filter(record -> !uniqueRecords.get(record.getHallTicketNumber()).getId().equals(record.getId()))
            .collect(Collectors.toList());
        
        // Delete duplicates
        int deletedCount = toDelete.size();
        repository.deleteAll(toDelete);
        
        Map<String, Object> result = new HashMap<>();
        result.put("message", "Duplicates removed successfully");
        result.put("deletedCount", deletedCount);
        result.put("remainingRecords", uniqueRecords.size());
        
        return result;
    }
}

