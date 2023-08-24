package org.kvto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Segment {

    public static int GreenScore = 200;
    public static int CostScore = 1;
    public static int WasteScore = 100;


    Splittable root;
    List<Splittable> ends;
    List<Splittable> combination;
    HashSet<Splittable> visited;
    HashSet<SplittableUnit> functions;

    Segment(List<Splittable> combination) {

        this.combination = combination;
        root = combination.get(0);
        ends = new ArrayList<Splittable>();
        for (Splittable s : combination) {

            for (Splittable neighbor : s.getNeighbors()) {
                if (combination.contains(neighbor)) continue;
                ends.add(neighbor);
            }
        }

        visited.addAll(combination);


        this.functions = new HashSet<>();
        for (Splittable splittable : combination) {

            if (splittable instanceof SplittableGroup) {
                functions.addAll(((SplittableGroup) splittable).splittableUnits);
            }
            if (splittable instanceof SplittableUnit) {
                functions.add((SplittableUnit) splittable);
            }
        }
//


    }

    String Generate(Module module) {
        StringBuilder b = new StringBuilder();

        b.append(GenFrame(module));
        for (SplittableUnit function : functions) {
            b.append(function.identifier).append(":\n");
            b.append(function.GenBody(module.generator));
        }
        return b.toString();

    }

    String GenFrame(Module module) {
        if (this.functions.contains(module.mainFunction)) {
            return module.GenMainFrame(module.generator);
        } else {
            return module.GenSubFrame(module.generator);
        }

    }

    public int evaluate() {
        int score = 0;

        for (Splittable node : combination) {

            List<Splittable> neighbors = node.getNeighbors();

            int count = 0;
            for (Splittable neighbor : neighbors) {
                if (neighbor.mode == mode.green) count++;
            }

            if (count > 1) score += (count - 1) * count * GreenScore;


        }

        int totalCost = 0;

        for (Splittable node : visited) {
            totalCost += node.cost;
        }

        score += totalCost * CostScore;

        for (Splittable end : ends) {
            if (end.depenCost < 800) {
                score -= (800 - end.depenCost) * WasteScore;
            }
        }

        return score;


    }




}
