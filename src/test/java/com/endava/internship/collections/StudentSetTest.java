package com.endava.internship.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentSetTest {
    StudentSet studentSetTest;
    Student student1;
    Student student2;
    Student student3;
    Student student4;

    @BeforeEach
    public void setUp() {
        studentSetTest = new StudentSet();
        student1 = new Student("A", LocalDate.of(1990, 11, 1), "");
        student2 = new Student("B", LocalDate.of(1990, 11, 1), "");
        student3 = new Student("C", LocalDate.of(1990, 11, 1), "");
        student4 = new Student("D", LocalDate.of(1990, 11, 1), "");
    }

    @Test
    void testSizeWhenSizeIsZero() {
        assertEquals(0, studentSetTest.size(), "Collection show the wrong size");
    }

    @Test
    void testSizeWhenSizeIsNotZero() {
        studentSetTest.add(student1);
        assertEquals(1, studentSetTest.size(), "Collection show the wrong size");
    }

    @Test
    void testIsEmptyWhenIsEmpty() {
        assertTrue(studentSetTest.isEmpty(), "The collection is not empty!");
    }

    @Test
    void testIsEmptyWhenIsNotEmpty() {
        studentSetTest.add(student1);
        assertFalse(studentSetTest.isEmpty(), "The collection is empty!");
    }

    @Test
    void testContains() {
        studentSetFilling();

        assertTrue(studentSetTest.contains(student1), "Collection cannot find existing element in itself");
        assertTrue(studentSetTest.contains(student2), "Collection cannot find existing element in itself");
        assertFalse(studentSetTest.contains(student3), "Collection could find in itself a nonexistent element");
    }

    @Test
    void testAddSameElement() {
        studentSetTest.add(student1);
        assertFalse(studentSetTest.add(student1), "Collection could add in itself a existent element");
    }


    @Test
    void testContainsNonStudentObject() {
        final Object object = new Object();
        assertFalse(studentSetTest.contains(object), "The collection contains an object of another class");
        assertFalse(studentSetTest.contains(null), "The collection contains null");
    }

    @Test
    void testIterator() {
        studentSetTest.add(student2);
        studentSetTest.add(student1);
        studentSetTest.add(student4);
        studentSetTest.add(student3);

        final Iterator<Student> iterator = studentSetTest.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext(), "Iterator not working properly");
    }

    @Test
    void testToArray() {
        studentSetFilling();

        final Object[] input = new Object[]{student1, student2};
        final Object[] result = studentSetTest.toArray();

        assertArrayEquals(result, input, "Incoming and outgoing arrays do not match");
    }

    @Test
    void testToArrayObject() {
        studentSetFilling();

        final Object[] input = new Object[]{student1, student2};
        final Object[] result = studentSetTest.toArray(new Object[0]);

        assertArrayEquals(result, input, "Incoming and outgoing arrays do not match");
    }

    @Test
    void testRemoveNonStudentObject() {
        final Object object = new Object();

        assertFalse(studentSetTest.remove(object), "Collection could remove in itself a nonexistent element");
        assertFalse(studentSetTest.remove(null), "Collection could remove in itself null");
    }

    @Test
    void testRemoveWhenCollectionDoesNotHaveThatElement() {
        studentSetFilling();

        assertFalse(studentSetTest.remove(student3), "Collection could remove in itself a nonexistent element");
    }

    @Test
    void testRemoveWhenElementHasNoChild() {
        studentSetTest.add(student1);
        studentSetTest.remove(student1);

        assertTrue(studentSetTest.isEmpty(), "After removing a single element, the collection thinks it is not empty");
        assertEquals(0, studentSetTest.size(), "After removing a single element, the collection size is wrong");
    }

    @Test
    void testRemoveWhenElementHasOneLeftChild() {
        studentSetTest.add(student3);
        studentSetTest.add(student2);
        studentSetTest.add(student1);

        studentSetTest.remove(student2);

        assertFalse(studentSetTest.contains(student2), "Collection contains the removed element");

        studentSetTest.remove(student1);
        assertFalse(studentSetTest.contains(student1), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenLeftElementHasOneLeftChild() {
        studentSetTest.add(student2);
        studentSetTest.add(student1);

        studentSetTest.remove(student2);

        assertFalse(studentSetTest.contains(student2), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenRightElementHasOneLeftChild() {
        studentSetTest.add(student1);
        studentSetTest.add(student3);
        studentSetTest.add(student2);

        studentSetTest.remove(student3);

        assertFalse(studentSetTest.contains(student3), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenRightElementHasOneRightChild() {
        studentSetFilling();
        studentSetTest.add(student3);

        studentSetTest.remove(student2);

        assertFalse(studentSetTest.contains(student2), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenLeftElementHasOneRightChild() {
        studentSetTest.add(student3);
        studentSetTest.add(student1);
        studentSetTest.add(student2);

        studentSetTest.remove(student1);

        assertFalse(studentSetTest.contains(student1), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenElementHasTwoChildren() {
        studentSetTest.add(student2);
        studentSetTest.add(student1);
        studentSetTest.add(student3);

        studentSetTest.remove(student2);

        assertFalse(studentSetTest.contains(student2), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenElementHasTwoChildrenAndSuccessorHasRight() {
        studentSetTest.add(student2);
        studentSetTest.add(student1);
        studentSetTest.add(student3);
        studentSetTest.add(student4);

        studentSetTest.remove(student2);

        assertFalse(studentSetTest.contains(student2), "Collection contains the removed element");
    }

    @Test
    void testClear() {
        studentSetFilling();

        studentSetTest.clear();

        assertTrue(studentSetTest.isEmpty(), "After cleaning, the collection thinks it is not empty");
        assertEquals(0, studentSetTest.size(), "After cleaning, the collection size is wrong");
    }

    @Test
    void testAddAll() {
        studentSetFilling();

        final List<Student> students = new ArrayList<>();
        students.add(student3);
        students.add(student4);

        studentSetTest.addAll(students);

        assertTrue(studentSetTest.contains(student3), "The collection does not contains items added by the addAll method");
        assertTrue(studentSetTest.contains(student4), "The collection does not contains items added by the addAll method");
    }

    @Test
    void testContainsAll() {
        studentSetFilling();

        final List<Student> studentList = new ArrayList<>();
        studentList.add(student2);
        studentList.add(student1);

        assertTrue(studentSetTest.containsAll(studentList), "The collection does not contain required elements");
    }

    @Test
    void testContainsAllWhenUnsupportedOperation() {
        studentSetFilling();

        final List<Object> objectsList = new ArrayList<>();
        objectsList.add(new Object());
        objectsList.add(new Object());

        assertThrows(UnsupportedOperationException.class, () -> studentSetTest.containsAll(objectsList));
    }

    @Test
    void testRemoveAllWhenUnsupportedOperation() {
        studentSetFilling();

        final List<Object> objectsList = new ArrayList<>();
        objectsList.add(new Object());
        objectsList.add(new Object());

        assertThrows(UnsupportedOperationException.class, () -> studentSetTest.removeAll(objectsList));
    }

    @Test
    void testRetainAll() {
        studentSetFilling();

        final List<Student> students = new ArrayList<>();
        students.add(student2);
        students.add(student3);

        studentSetTest.retainAll(students);
        assertEquals(1, studentSetTest.size(), "After executing the method, the size of the collection is incorrect");
        assertTrue(studentSetTest.contains(student2), "After executing the method, the collection does not contain the required elements");
    }

    @Test
    void testRemoveAll() {
        studentSetFilling();
        studentSetTest.add(student3);
        studentSetTest.add(student4);

        final List<Student> students = new ArrayList<>();
        students.add(student2);
        students.add(student4);

        studentSetTest.removeAll(students);

        assertFalse(studentSetTest.containsAll(students), "The removeAll method did not remove the item from collection");
        assertEquals(2, studentSetTest.size(), "After executing the method, the size of the collection is incorrect");
    }

    private void studentSetFilling() {
        studentSetTest.add(student1);
        studentSetTest.add(student2);
    }
}