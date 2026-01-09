package com.example.demo;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentMarksRepository extends JpaRepository<StudentMarks, Long> {
    
    Optional<StudentMarks> findByHallTicketNumber(String hallTicketNumber);
    
    @Query("SELECT s FROM StudentMarks s WHERE s.hallTicketNumber IN (SELECT s2.hallTicketNumber FROM StudentMarks s2 GROUP BY s2.hallTicketNumber HAVING COUNT(s2) > 1)")
    java.util.List<StudentMarks> findDuplicates();
}

