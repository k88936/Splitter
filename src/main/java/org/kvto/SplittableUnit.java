package org.kvto;

import java.util.ArrayList;



public abstract class SplittableUnit extends Splittable {

    public String identifier;

    // protected int cost;
    public int FuncId;

    /**
     * @param identifier label name to be jumped to
     * @param cost       counts of code
     */

    SplittableUnit(String identifier, int cost) {


        this.identifier = identifier;


        this.cost = cost;
    }


    protected ArrayList<SplittableUnit> dependency = new ArrayList<>();

    /**
     * pass the code body here ,no modification is needed
     *
     * @param generator
     * @return
     */
    abstract String GenBody(Generator generator);

    @Override
    public int getCost() {
        return cost;
    }

    /**
     * judge whether a function make some side effects
     * just focus on the code in your function
     * Splitter will finally judge it according to the functions it calls
     * <p>
     * RED------must wait untill it returns
     * GREED----allow asynchronization but delayed evaluate is needed
     */
    abstract mode judgeMode();

    abstract ArrayList<String> dependency();

    public void setFuncId(int funcId) {
        FuncId = funcId;
    }


}
