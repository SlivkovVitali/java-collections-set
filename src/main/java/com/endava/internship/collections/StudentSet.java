package com.endava.internship.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StudentSet implements Set<Student> {
    private Node root;
    private List<Student> list;
    private int size = 0;

    public StudentSet() {
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Student)) {
            return false;
        }

        Node nodeToSearch = new Node((Student) o);
        return search(root, nodeToSearch) != null;
    }

    private Node search(Node sourceNode, Node nodeToSearch) {
        int compare = sourceNode.compareTo(nodeToSearch);

        if (compare < 0 && sourceNode.right != null) {
            return search(sourceNode.right, nodeToSearch);
        }

        if (compare > 0 && sourceNode.left != null) {
            return search(sourceNode.left, nodeToSearch);
        }

        if (compare == 0) {
            return sourceNode;
        }

        return null;
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<Student>() {
            final Iterator<Node> iterator = new TreeIterator(root);

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Student next() {
                return iterator.next().student;
            }
        };
    }

    @Override
    public Object[] toArray() {
        final Object[] result = new Object[this.size];
        int i = 0;

        for (Object element : this) {
            result[i++] = element;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        T[] result = (T[]) new Object[this.size()];
        System.arraycopy(list, 0, result, 0, this.size());
        return result;
    }

    @Override
    public boolean add(Student student) {
        if (size == 0) {
            root = new Node(student);
            size++;
            return true;
        }

        Node newNode = new Node(student);
        Node lastNode = findLastNode(root, newNode);

        if (lastNode == null) {
            return false;
        }

        size++;
        newNode.parent = lastNode;

        if (lastNode.compareTo(newNode) < 0) {
            lastNode.right = newNode;
        } else {
            lastNode.left = newNode;
        }
        return true;
    }

    private Node findLastNode(final Node oldNode, final Node newNode) {
        Node lastNode = oldNode;
        int compare = oldNode.compareTo(newNode);

        if (compare < 0 && oldNode.right != null) {
            lastNode = findLastNode(oldNode.right, newNode);
            return lastNode;
        }

        if (compare > 0 && oldNode.left != null) {
            lastNode = findLastNode(oldNode.left, newNode);
        }

        if (compare == 0) {
            return null;
        }

        return lastNode;
    }

    @Override
    public boolean remove(Object o) {
        //TODO
        return false;
    }

    @Override
    public void clear() {
        list = new LinkedList<>();
        size = 0;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        for (Student student : collection) {
            this.add(student);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    class Node implements Comparable<Node> {
        Node parent;
        Node right;
        Node left;
        Student student;

        private Node(Student student) {
            this.student = student;
        }

        @Override
        public int compareTo(Node nodeToCompare) {
            return this.student.compareTo(nodeToCompare.student);
        }
    }
}
