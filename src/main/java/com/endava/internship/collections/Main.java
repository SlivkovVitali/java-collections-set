package com.endava.internship.collections;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentSet studentSet = new StudentSet();
        Student student1 = new Student("C", LocalDate.of(1990, 11, 1), "");
        Student student2 = new Student("B", LocalDate.of(1990, 11, 1), "3");
        Student student3 = new Student("A", LocalDate.of(1990, 11, 3), "");
        Student student4 = new Student("D", LocalDate.of(1990, 11, 4), "");
        Student student5 = new Student("E", LocalDate.of(1990, 11, 5), "");
        studentSet.add(student1);
        studentSet.add(student2);
        studentSet.add(student3);
        studentSet.add(student4);
        studentSet.add(student5);
        System.out.println("size = " + studentSet.size());

        for (Student st : studentSet) {
            System.out.print(st.getName() + " ");
        }
        studentSet.clear();
        System.out.println();
        System.out.println("isEmpty = " + studentSet.isEmpty());
        System.out.println();

        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);

        studentSet.addAll(list);
        Object[] st = studentSet.toArray();


        for (Object st1 : st) {
            Student s = (Student) st1;
            System.out.print(s.getDateOfBirth() + " - ");
        }
        System.out.println();
        System.out.println("contains = " + studentSet.contains(student2));
    }
}
