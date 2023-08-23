package org.kvto;

import java.util.ArrayList;
import java.util.List;

enum mode {
    green,
    red,
    blue
}
class Splittable {
    private final List<Splittable> neighbors;
    public int depenCost = 0;
    private int id;
    public int cost;
    public mode mode;


    public Splittable() {
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(Splittable neighbor) {
        neighbors.add(neighbor);
    }

    int getCost() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public List<Splittable> getNeighbors() {
        return neighbors;
    }

    public void setID(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        return "Splittable " + id;
    }
}
