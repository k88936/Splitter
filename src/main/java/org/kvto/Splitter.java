package org.kvto;

import java.util.ArrayList;

public class Splitter {

    private final Module module;

    Splitter(Module module) {


        this.module = module;
        var mainFunction = this.module.mainFunction;

        var functions = this.module.functions;
        functions.values().forEach(f -> {


            f.mode = f.judgeMode();
            f.dependency().forEach(name -> {
                f.dependency.add(functions.get(name));
            });

        });

        var callGraph = new Analysis(functions.values());


        callGraph.replaceCyclesWithSplittables();
        int total = callGraph.computeCost(mainFunction);
        var combinations = callGraph.findCombinations(mainFunction);
        ArrayList<Segment> split = callGraph.split(mainFunction, new ArrayList<Segment>());


    }




}
