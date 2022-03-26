package com.endava.internship.collections;

import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {

        StudentSet studentSet = new StudentSet();
        Student student1 = new Student("B", LocalDate.of(1990, 11, 1), "");
        Student student2 = new Student("A", LocalDate.of(1990, 11, 1), "");
        Student student3 = new Student("O", LocalDate.of(1990, 11, 1), "");
        Student student4 = new Student("C", LocalDate.of(1990, 11, 1), "");
        Student student5 = new Student("S", LocalDate.of(1990, 11, 1), "");
        Student student6 = new Student("P", LocalDate.of(1990, 11, 1), "");
        Student student7 = new Student("W", LocalDate.of(1990, 11, 1), "");
        Student student8 = new Student("U", LocalDate.of(1990, 11, 1), "");
        Student student9 = new Student("Z", LocalDate.of(1990, 11, 1), "");
        Student student10 = new Student("V", LocalDate.of(1990, 11, 1), "");
//        Student student11 = new Student("T", LocalDate.of(1990, 11, 1), "");
//        Student student12 = new Student("Y", LocalDate.of(1990, 11, 1), "");
//        Student student13 = new Student("X", LocalDate.of(1990, 11, 1), "");
//        Student student14 = new Student("Z", LocalDate.of(1990, 11, 1), "");

        studentSet.add(student1);
        studentSet.add(student2);
        studentSet.add(student3);
        studentSet.add(student4);
        studentSet.add(student5);
        studentSet.add(student6);
        studentSet.add(student7);
        studentSet.add(student8);
        studentSet.add(student9);
        studentSet.add(student10);

        for (Student st : studentSet) {
            System.out.print(st.getName() + " ");
        }

        System.out.println();

        System.out.println("size = " + studentSet.size());

        studentSet.remove(student5);
        studentSet.remove(student8);
        studentSet.remove(student7);
        studentSet.remove(student1);
        studentSet.remove(student4);
        studentSet.remove(student9);
        studentSet.remove(student10);

        System.out.println("size = " + studentSet.size());

        for (Student st : studentSet) {
            System.out.print(st.getName() + " ");
        }
    }
}