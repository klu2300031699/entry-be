package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "student_marks")
public class StudentMarks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String hallTicketNumber;
    private String setNumber;
    private String marks;

    public StudentMarks() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getHallTicketNumber() { return hallTicketNumber; }
    public void setHallTicketNumber(String hallTicketNumber) {
        this.hallTicketNumber = hallTicketNumber;
    }

    public String getSetNumber() { return setNumber; }
    public void setSetNumber(String setNumber) {
        this.setNumber = setNumber;
    }

    public String getMarks() { return marks; }
    public void setMarks(String marks) {
        this.marks = marks;
    }
}
