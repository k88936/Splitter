package org.kvto;

public class Splitter {

    private final Module module;

    Splitter(Module module) {


        this.module = module;
        var mainFunction = this.module.mainFunction;

        var functions = this.module.functions;
        functions.values().forEach(f -> {


            f.dependency().forEach(name -> {
                f.dependency.add(functions.get(name));
            });

        });

        var callGraph = new CallGraph();
        for (SplittableUnit splittableUnit : functions.values()) {

            callGraph.addSplittable(splittableUnit);
            for (SplittableUnit unit : splittableUnit.dependency) {
                splittableUnit.addNeighbor(unit);
            }


        }

        callGraph.replaceCyclesWithSplittables();
        int total = callGraph.computeCost(mainFunction);
        var combinations = callGraph.findCombinations(mainFunction);


    }




}
