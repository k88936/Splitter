package org.kvto;

import java.util.List;

public class SplittableGroup extends Splittable {
    List<SplittableUnit> splittableUnits;

    public SplittableGroup(List<Splittable> splittableUnits) {
        for (Splittable splittableUnit : splittableUnits) {
            this.splittableUnits
                    .add((SplittableUnit) splittableUnit);
        }

    }

    @Override
    int getCost() {
        int cost = 0;
        for (SplittableUnit splittableUnit : splittableUnits) {
            cost += splittableUnit.getCost();
        }
        return cost;
    }
}
