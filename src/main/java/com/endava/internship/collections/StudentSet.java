package com.endava.internship.collections;

import java.util.*;

public class StudentSet implements Set<Student> {
        private Node root;
        private List<Student> list;
        private int size = 0;

        public StudentSet(){
            list = new LinkedList<>();
            root = new Node(null); //??
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
            if (!(o instanceof Student)){
                return false;
            }

            Node eNode = new Node((Student) o);
            return search(root, eNode) != null;
        }

        private Node search(Node node, Node eNode){
            int compare = node.compareTo(eNode);

            if (compare < 0 && node.right != null){
                return search(node.right, eNode);
            }

            if (compare > 0 && node.left != null){
                return search(node.left, eNode);
            }

            if (compare == 0){
                return node;
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

        private class TreeIterator implements Iterator<Node>{
            private Node next;

            private TreeIterator(Node root){
                next = root;
                goLeft();
            }

            private void goLeft() {
                while (next.left != null){
                    next = next.left;
                }
            }

            @Override
            public boolean hasNext(){
                return next != null && next.student != null;
            }

            @Override
            public Node next(){
                Node r = next;

                if (next.right != null){
                    return goRight(r);
                }

                return goUp(r);
            }

            private Node goRight(Node r){
                next = next.right;
                while (next.left != null){
                    next = next.left;
                }
                return r;
            }

            private Node goUp(Node u) {
                while (true){
                    if (next.parent == null){
                        next = null;
                        return u;
                    }

                    if (next.parent.left == next){
                        next = next.parent;
                        return u;
                    }

                    next = next.parent;
                }
            }
        }

        @Override
        public Object[] toArray() {
            final Object[] result = new Object[this.size];
            int i = 0;

            for (Object element: this) {
                result[i++] = element;
            }
            return result;
        }

        @Override
        public <T> T[] toArray(T[] ts) {
//        T[] result = (T[]) new Object[this.size()];
//        System.arraycopy(list, 0, result, 0, this.size());
//        return result;
            return (T[]) this.toArray();
        }

        @Override
        public boolean add(Student student) {
            if (size == 0){
                root.student = student;
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

        private Node findLastNode(final Node oldNode, final Node newNode){
            Node lastNode = oldNode;
            int compare = oldNode.compareTo(newNode);

            if (compare < 0 && oldNode.right != null) {
                lastNode = findLastNode(oldNode.right, newNode);
                return lastNode;
            }

            if (compare > 0 && oldNode.left != null) {
                lastNode = findLastNode(oldNode.left, newNode);
            }

            if (compare == 0){
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
            root = new Node(null); //??
            size = 0;
        }

        @Override
        public boolean addAll(Collection<? extends Student> collection) {
            for (Student student: collection) {
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

        class Node {
            private Node parent;
            private Node right;
            private Node left;
            private Student student;

            private Node(Student student){
                this.student = student;
            }

            public int compareTo(Object o) {
                Node newNode = (Node) o;
                int compareName = this.student.getName().compareTo(newNode.student.getName());
                if (compareName != 0) {
                    return compareName;
                } else {
                    return this.student.getDateOfBirth().compareTo(newNode.student.getDateOfBirth());
                }
            }
        }

    }
