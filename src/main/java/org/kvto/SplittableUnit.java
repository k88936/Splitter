package org.kvto;

import java.util.ArrayList;



public abstract class SplittableUnit extends Splittable {

    public String identifier;

    // protected int cost;
    public int FuncId;

    SplittableUnit(String identifier, int cost) {


        this.identifier = identifier;


        this.cost = cost;
    }


    protected ArrayList<SplittableUnit> dependency = new ArrayList<>();

    abstract String GenBody(Generator generator);

    @Override
    public int getCost() {
        return cost;
    }

    abstract mode judgeMode();

    abstract ArrayList<String> dependency();

    public void setFuncId(int funcId) {
        FuncId = funcId;
    }


}
