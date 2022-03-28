package com.endava.internship.collections;

import com.endava.internship.collections.StudentSet.Node;

import java.util.Iterator;

    public class TreeIterator implements Iterator<Node> {
        private Node next;

        public TreeIterator(final Node root){
            next = root;
            goLeftMost();
        }

        private void goLeftMost() {
            while (next.getLeft() != null){
                next = next.getLeft();
            }
        }

        @Override
        public boolean hasNext(){
            return next != null && next.getStudent() != null;
        }

        @Override
        public Node next(){
            Node sourceNode = next;

            if (next.getRight() != null){
                return goRightAndLeftMost(sourceNode);
            }

            return goUp(sourceNode);
        }

        private Node goRightAndLeftMost(final Node sourceNode){
            next = next.getRight();
            while (next.getLeft() != null){
                next = next.getLeft();
            }
            return sourceNode;
        }

        private Node goUp(final Node sourceNode) {
            while (true){
                if (next.getParent() == null){
                    next = null;
                    return sourceNode;
                }

                if (next.getParent().getLeft() == next){
                    next = next.getParent();
                    return sourceNode;
                }

                next = next.getParent();
            }
        }
    }
