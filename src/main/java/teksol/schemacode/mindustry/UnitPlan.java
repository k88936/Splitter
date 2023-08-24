package teksol.schemacode.mindustry;

import teksol.schemacode.config.Configuration;
import teksol.schemacode.config.EmptyConfiguration;
import teksol.schemacode.config.IntConfiguration;
import teksol.schemacode.schema.Block;

public record UnitPlan(String unitName) implements Configuration {

    public static final UnitPlan EMPTY = new UnitPlan("");

    @Override
    public Configuration encode(Block block) {
        int index = block.blockType().unitPlans().indexOf(unitName);
        return index < 0 ? EmptyConfiguration.EMPTY : new IntConfiguration(index);
    }
}
