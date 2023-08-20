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

        var directedGraph = new DirectedGraph();
        for (SplittableUnit splittableUnit : functions.values()) {

            directedGraph.addNode(splittableUnit);
            for (SplittableUnit unit : splittableUnit.dependency) {
                splittableUnit.addNeighbor(unit);
            }


            directedGraph.replaceCyclesWithNodes();


        }

//        int totalCost = computeCost(mainFunction);
//
//        functions.values().stream().sorted(new Comparator<SplittableUnit>() {
//            @Override
//            public int compare(SplittableUnit o1, SplittableUnit o2) {
//                return o1.realCost - o2.realCost;
//            }
//        });


    }

    int computeCost(SplittableUnit splittableUnit) {


        int rc = splittableUnit.realCost;
        if (rc != -1) return rc;
        splittableUnit.realCost = 0;
        for (SplittableUnit unit : splittableUnit.dependency) {

            splittableUnit.realCost += computeCost(unit);

        }


        return splittableUnit.realCost = splittableUnit.realCost + splittableUnit.cost;
    }

    void select(SplittableUnit splittableUnit) {

    }


}
