package org.kvto;

import java.util.ArrayList;
import java.util.List;

class Node {
    private final List<Node> neighbors;
    private int id;

    public Node() {
//        this.id = id;
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setID(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        return "Node " + id;
    }
}
