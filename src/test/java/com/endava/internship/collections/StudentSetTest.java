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
    private StudentSet studentSetTest;
    private Student studentA;
    private Student studentB;
    private Student studentC;
    private Student studentD;

    @BeforeEach
    public void setUp() {
        studentSetTest = new StudentSet();
        studentA = new Student("A", LocalDate.of(1990, 11, 1), "");
        studentB = new Student("B", LocalDate.of(1990, 11, 1), "");
        studentC = new Student("C", LocalDate.of(1990, 11, 1), "");
        studentD = new Student("D", LocalDate.of(1990, 11, 1), "");
    }

    @Test
    void testSizeWhenSizeIsZero() {
        assertEquals(0, studentSetTest.size(), "Collection show the wrong size");
    }

    @Test
    void testSizeWhenSizeIsNotZero() {
        studentSetTest.add(studentA);
        assertEquals(1, studentSetTest.size(), "Collection show the wrong size");
    }

    @Test
    void testIsEmptyWhenIsEmpty() {
        assertTrue(studentSetTest.isEmpty(), "The collection is not empty!");
    }

    @Test
    void testIsEmptyWhenIsNotEmpty() {
        studentSetTest.add(studentA);
        assertFalse(studentSetTest.isEmpty(), "The collection is empty!");
    }

    @Test
    void testContains() {
        studentSetFilling();

        assertTrue(studentSetTest.contains(studentA), "Collection cannot find existing element in itself");
        assertTrue(studentSetTest.contains(studentB), "Collection cannot find existing element in itself");
        assertFalse(studentSetTest.contains(studentC), "Collection could find in itself a nonexistent element");
    }

    @Test
    void testAddSameElement() {
        studentSetTest.add(studentA);
        assertFalse(studentSetTest.add(studentA), "Collection could add in itself a existent element");
    }


    @Test
    void testContainsNonStudentObject() {
        final Object object = new Object();
        assertFalse(studentSetTest.contains(object), "The collection contains an object of another class");
        assertFalse(studentSetTest.contains(null), "The collection contains null");
    }

    @Test
    void testIterator() {
        studentSetTest.add(studentB);
        studentSetTest.add(studentA);
        studentSetTest.add(studentD);
        studentSetTest.add(studentC);

        final Iterator<Student> iterator = studentSetTest.iterator();
        for (int i = 0; i < 4; i++) {
            iterator.next();

        }
        assertFalse(iterator.hasNext(), "Iterator not working properly");
    }

    @Test
    void testToArray() {
        studentSetFilling();

        final Object[] input = new Object[]{studentA, studentB};
        final Object[] result = studentSetTest.toArray();

        assertArrayEquals(result, input, "Incoming and outgoing arrays do not match");
    }

    @Test
    void testToArrayObject() {
        studentSetFilling();

        final Object[] input = new Object[]{studentA, studentB};
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

        assertFalse(studentSetTest.remove(studentC), "Collection could remove in itself a nonexistent element");
    }

    @Test
    void testRemoveWhenElementHasNoChild() {
        studentSetTest.add(studentA);
        studentSetTest.remove(studentA);

        assertTrue(studentSetTest.isEmpty(), "After removing a single element, the collection thinks it is not empty");
        assertEquals(0, studentSetTest.size(), "After removing a single element, the collection size is wrong");
    }

    @Test
    void testRemoveWhenElementHasOneLeftChild() {
        studentSetTest.add(studentC);
        studentSetTest.add(studentB);
        studentSetTest.add(studentA);

        studentSetTest.remove(studentB);

        assertFalse(studentSetTest.contains(studentB), "Collection contains the removed element");

        studentSetTest.remove(studentA);
        assertFalse(studentSetTest.contains(studentA), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenLeftElementHasOneLeftChild() {
        studentSetTest.add(studentB);
        studentSetTest.add(studentA);

        studentSetTest.remove(studentB);

        assertFalse(studentSetTest.contains(studentB), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenRightElementHasOneLeftChild() {
        studentSetTest.add(studentA);
        studentSetTest.add(studentC);
        studentSetTest.add(studentB);

        studentSetTest.remove(studentC);

        assertFalse(studentSetTest.contains(studentC), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenRightElementHasOneRightChild() {
        studentSetFilling();
        studentSetTest.add(studentC);
        studentSetTest.remove(studentB);

        assertFalse(studentSetTest.contains(studentB), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenLeftElementHasOneRightChild() {
        studentSetTest.add(studentC);
        studentSetTest.add(studentA);
        studentSetTest.add(studentB);

        studentSetTest.remove(studentA);

        assertFalse(studentSetTest.contains(studentA), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenElementHasTwoChildren() {
        studentSetTest.add(studentB);
        studentSetTest.add(studentA);
        studentSetTest.add(studentC);

        studentSetTest.remove(studentB);

        assertFalse(studentSetTest.contains(studentB), "Collection contains the removed element");
    }

    @Test
    void testRemoveWhenElementHasTwoChildrenAndSuccessorHasRight() {
        studentSetTest.add(studentB);
        studentSetTest.add(studentA);
        studentSetTest.add(studentC);
        studentSetTest.add(studentD);

        studentSetTest.remove(studentB);

        assertFalse(studentSetTest.contains(studentB), "Collection contains the removed element");
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
        students.add(studentC);
        students.add(studentD);

        studentSetTest.addAll(students);

        assertTrue(studentSetTest.contains(studentC), "The collection does not contains items added by the addAll method");
        assertTrue(studentSetTest.contains(studentD), "The collection does not contains items added by the addAll method");
    }

    @Test
    void testContainsAll() {
        studentSetFilling();

        final List<Student> studentList = new ArrayList<>();
        studentList.add(studentB);
        studentList.add(studentA);

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
        students.add(studentB);
        students.add(studentC);

        studentSetTest.retainAll(students);
        assertEquals(1, studentSetTest.size(), "After executing the method, the size of the collection is incorrect");
        assertTrue(studentSetTest.contains(studentB), "After executing the method, the collection does not contain the required elements");
    }

    @Test
    void testRemoveAll() {
        studentSetFilling();
        studentSetTest.add(studentC);
        studentSetTest.add(studentD);

        final List<Student> students = new ArrayList<>();
        students.add(studentB);
        students.add(studentD);

        studentSetTest.removeAll(students);

        assertFalse(studentSetTest.containsAll(students), "The removeAll method did not remove the item from collection");
        assertEquals(2, studentSetTest.size(), "After executing the method, the size of the collection is incorrect");
    }

    private void studentSetFilling() {
        studentSetTest.add(studentA);
        studentSetTest.add(studentB);
    }
}