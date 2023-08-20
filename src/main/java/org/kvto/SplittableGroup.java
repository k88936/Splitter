package org.kvto;

import java.util.List;

public class SplittableGroup extends Node {
    List<SplittableUnit> splittableUnits;

    public SplittableGroup(List<Node> splittableUnits) {
        for (Node splittableUnit : splittableUnits) {
            this.splittableUnits
                    .add((SplittableUnit) splittableUnit);
        }

    }
}
