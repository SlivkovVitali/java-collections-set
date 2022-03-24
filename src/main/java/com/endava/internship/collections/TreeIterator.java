package com.endava.internship.collections;

import java.util.Iterator;

    public class TreeIterator implements Iterator<StudentSet.Node> {
        private StudentSet.Node next;

        TreeIterator(StudentSet.Node root){
            next = root;
            goLeftMost();
        }

        private void goLeftMost() {
            while (next.left != null){
                next = next.left;
            }
        }

        @Override
        public boolean hasNext(){
            return next != null && next.student != null;
        }

        @Override
        public StudentSet.Node next(){
            StudentSet.Node sourceNode = next;

            if (next.right != null){
                return goRight(sourceNode);
            }

            return goUp(sourceNode);
        }

        private StudentSet.Node goRight(StudentSet.Node sourceNode){
            next = next.right;
            while (next.left != null){
                next = next.left;
            }
            return sourceNode;
        }

        private StudentSet.Node goUp(StudentSet.Node sourceNode) {
            while (true){
                if (next.parent == null){
                    next = null;
                    return sourceNode;
                }

                if (next.parent.left == next){
                    next = next.parent;
                    return sourceNode;
                }

                next = next.parent;
            }
        }
    }
