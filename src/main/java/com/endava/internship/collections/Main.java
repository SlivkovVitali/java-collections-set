package com.endava.internship.collections;

import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {

        StudentSet studentSet = new StudentSet();
        Student student1 = new Student("A", LocalDate.of(1990, 11, 1), "");
        Student student2 = new Student("B", LocalDate.of(1990, 11, 1), "");
        Student student3 = new Student("C", LocalDate.of(1990, 11, 1), "");
        Student student4 = new Student("D", LocalDate.of(1990, 11, 5), "");
        Student student5 = new Student("E", LocalDate.of(1990, 11, 4), "");
        studentSet.add(student1);
        studentSet.add(student2);
        studentSet.add(student3);
        studentSet.add(student4);
        studentSet.add(student5);

        for (Student st : studentSet) {
            System.out.print(st.getName() + " - ");
        }

        System.out.println(studentSet.size());

        studentSet.remove(student3);

        System.out.println(studentSet.size());

        for (Student st : studentSet) {
            System.out.print(st.getName() + " - ");
        }
    }
}
