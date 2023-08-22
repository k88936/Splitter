package org.kvto;

import java.util.ArrayList;

enum mode {
    green,
    effective,
    sync
}

public abstract class SplittableUnit extends Splittable {

    public String identifier;
    protected int cost;

    mode mode;
    protected ArrayList<SplittableUnit> dependency = new ArrayList<>();

    SplittableUnit(String identifier, mode mode, int cost) {


        this.identifier = identifier;
        this.mode = mode;

        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    abstract String GenBody();

    abstract ArrayList<String> dependency();


}
