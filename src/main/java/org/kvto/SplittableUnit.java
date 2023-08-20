package org.kvto;

import java.util.ArrayList;

enum mode {
    green,
    effective,
    sync
}

public abstract class SplittableUnit extends Node {

    public String identifier;
    protected int cost;
    protected int realCost;
    protected ArrayList<SplittableUnit> dependency = new ArrayList<>();

    SplittableUnit(String identifier, mode mode, int cost) {
        super(0);

        this.identifier = identifier;
        this.realCost = -1;

        this.cost = cost;
    }

    abstract String GenBody();

    abstract ArrayList<String> dependency();


}
