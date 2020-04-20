package day9;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MarbleMania {
    private CircularLinkedList circle = new CircularLinkedList();

    long playGame(int players, int lastMarble) {
        Map<Integer, Long> scores = new HashMap<>();
        for (int i = 1; i <= players; i++) {
            scores.put(i, 0L);
        }
        int marblePoints;
        int marbleNo = 0;
        int playerId = -1;
        while (marbleNo <= lastMarble) {
            if (++playerId > players) {
                playerId = 1;
            }
            marblePoints = circle.putMarble(marbleNo);
            if (marblePoints != 0) {
                scores.put(playerId, scores.get(playerId) + marblePoints);
            }
            ++marbleNo;
        }
        return Collections.max(scores.entrySet(), Map.Entry.comparingByValue()).getValue();
    }

    class CircularLinkedList {

        public void setCurrentNode(Node currentNode) {
            this.currentNode = currentNode;
        }

        private Node currentNode;

        public void addNode(int value) {
            Node newNode = new Node(value);
            if (currentNode == null) {
                // empty list
                currentNode = newNode;
                currentNode.setPrevious(newNode);
                currentNode.setNext(newNode);
            } else {
                newNode.setNext(currentNode.getNext().getNext());
                currentNode.getNext().getNext().setPrevious(newNode);
                newNode.setPrevious(currentNode.getNext());
                currentNode.getNext().setNext(newNode);
                currentNode = newNode;
            }
        }

        public int deleteNode(Node node) {
            node.getNext().setPrevious(node.getPrevious());
            node.getPrevious().setNext(node.getNext());
            return node.getValue();
        }

        public int putMarble(int marbleNo) {
            if (marbleNo % 23 == 0 && marbleNo != 0) {
                int score = marbleNo;
                Node marbleToRemove = currentNode.getPrevious().getPrevious().getPrevious().getPrevious().getPrevious()
                        .getPrevious().getPrevious();
                setCurrentNode(marbleToRemove.getNext());
                score += deleteNode(marbleToRemove);
                return score;
            }
            circle.addNode(marbleNo);
            return 0;
        }
    }

    static class Node {
        private int value;

        private Node next;
        private Node previous;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }
    }
}
