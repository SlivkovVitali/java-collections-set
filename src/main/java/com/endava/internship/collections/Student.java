package com.endava.internship.collections;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * The class that defines the element that will be contained by your collection
 */

@Getter
@RequiredArgsConstructor
public class Student implements Comparable<Student>{

    private final String name;
    private final LocalDate dateOfBirth;
    private final String details;

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
