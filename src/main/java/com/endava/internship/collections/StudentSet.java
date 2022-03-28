package com.endava.internship.collections;


import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class StudentSet implements Set<Student> {
    private Node root;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(final Object o) {
        if (!(o instanceof Student)) {
            return false;
        }

        Node nodeToSearch = new Node((Student) o);
        return search(root, nodeToSearch) != null;
    }

    private Node search(final Node sourceNode, final Node nodeToSearch) {
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
        Object[] objects = toArray();
        ts = (T[]) Arrays.copyOf(objects, objects.length);
        return ts;
    }

    @Override
    public boolean add(final Student student) {
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
    public boolean remove(final Object o) {
        if (size == 0 || (!(o instanceof Student))) {
            return false;
        }

        Node nodeToRemove = new Node((Student) o);

        nodeToRemove = search(root, nodeToRemove);
        if (nodeToRemove == null) {
            return false;
        }

        if (nodeToRemove.getLeft() == null && nodeToRemove.getRight() == null) {
            return removeWhenNoChild(nodeToRemove);
        } else if (nodeToRemove.getLeft() == null) {
            return removeWhenOneRightChild(nodeToRemove);
        } else if (nodeToRemove.getRight() == null) {
            return removeWhenOneLeftChild(nodeToRemove);
        } else
            return removeWhenTwoChild(nodeToRemove);
    }

    private boolean removeWhenNoChild(final Node nodeToRemove) {
        if (nodeToRemove == root) {
            this.clear();
            root = null;
        } else {
            if (nodeToRemove.getParent().getLeft() == nodeToRemove) {
                nodeToRemove.getParent().setLeft(null);
            } else {
                nodeToRemove.getParent().setRight(null);
            }
            size--;
        }
        return true;
    }

    private boolean removeWhenOneRightChild(final Node nodeToRemove) {
        if (nodeToRemove == root) {
            root = nodeToRemove.getRight();
            nodeToRemove.getRight().setParent(null);
        } else
            nodeToRemove.getRight().setParent(nodeToRemove.getParent());

        if (nodeToRemove.getParent().getLeft() == nodeToRemove) {
            nodeToRemove.getParent().setLeft(nodeToRemove.getRight());
        } else {
            nodeToRemove.getParent().setRight(nodeToRemove.getRight());
        }
        size--;
        return true;
    }

    private boolean removeWhenOneLeftChild(final Node nodeToRemove) {
        if (nodeToRemove == root) {
            root = nodeToRemove.getLeft();
            nodeToRemove.getLeft().setParent(null);
        } else
            nodeToRemove.getLeft().setParent(nodeToRemove.getParent());

        if (nodeToRemove.getParent().getLeft() == nodeToRemove) {
            nodeToRemove.getParent().setLeft(nodeToRemove.getLeft());
        } else {
            nodeToRemove.getParent().setRight(nodeToRemove.getLeft());
        }
        size--;
        return true;
    }

    private boolean removeWhenTwoChild(final Node nodeToRemove) {

        Node successor = getSuccessor(nodeToRemove);
        nodeToRemove.setStudent(successor.getStudent());

        if (successor.getRight() == null) {
            removeWhenNoChild(successor);
        } else {
            removeWhenOneRightChild(successor);
        }
        return true;
    }

    private Node getSuccessor(final Node nodeToRemove) {
        Node successor = nodeToRemove;
        Node current = nodeToRemove.right;

        while (current != null) {
            successor = current;
            current = current.getLeft();
        }
        return successor;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean addAll(final Collection<? extends Student> collection) {
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
        private Node parent;
        private Node right;
        private Node left;
        private Student student;

        private Node(Student student) {
            this.student = student;
        }

        public Node getParent() {
            return parent;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public Student getStudent() {
            return student;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        @Override
        public int compareTo(final Node nodeToCompare) {
            return this.student.compareTo(nodeToCompare.student);
        }
    }
}
