package com.endava.internship.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentTest {
    Student student1;
    Student student2;
    Student student3;
    Student student4;

    @BeforeEach
    public void setUp() {
        student1 = new Student("A", LocalDate.of(1990, 11, 1), "");
        student2 = new Student("B", LocalDate.of(1990, 11, 1), "");
        student3 = new Student("B", LocalDate.of(1990, 11, 2), "");
        student4 = new Student("B", LocalDate.of(1990, 11, 1), "!");
    }

    @Test
    void testCompareTo() {
        int compare = student1.compareTo(student2);
        assertTrue(compare != 0, "Students with different names matches");

        compare = student3.compareTo(student4);
        assertTrue(compare != 0, "Students with different dates matches");
        assertEquals(0, student2.compareTo(student4), "Students with the same names and dates do not match");
    }
}
