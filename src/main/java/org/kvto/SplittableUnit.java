package org.kvto;

import java.util.ArrayList;



public abstract class SplittableUnit extends Splittable {

    public String identifier;
    // protected int cost;

   
    protected ArrayList<SplittableUnit> dependency = new ArrayList<>();

    SplittableUnit(String identifier, mode mode, int cost) {


        this.identifier = identifier;
        this.mode = mode;

        this.cost = cost;
    }

    @Override
    public int getCost() {
        return cost;
    }

    abstract String GenBody();

    abstract ArrayList<String> dependency();


}
