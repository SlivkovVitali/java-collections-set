package com.endava.internship.collections;

import java.time.LocalDate;

/**
 * The class that defines the element that will be contained by your collection
 */
public class Student implements Comparable<Student>{

    private String name;
    private LocalDate dateOfBirth;
    private String details;

    public Student(String name, LocalDate dateOfBirth, String details) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.details = details;
    }

    public String getName() { return name; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }

    public String getDetails() { return details; }

    @Override
    public int compareTo(Student student) {
        int compareName = name.compareTo(student.name);
        if (compareName != 0) {
            return compareName;
        } else {
            return dateOfBirth.compareTo(student.dateOfBirth);
        }
    }
}
