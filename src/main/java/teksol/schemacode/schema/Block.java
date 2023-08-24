package teksol.schemacode.schema;

import teksol.schemacode.config.Configuration;
import teksol.schemacode.config.PositionArray;
import teksol.schemacode.mimex.BlockType;
import teksol.schemacode.mindustry.Direction;
import teksol.schemacode.mindustry.Position;

import java.util.List;
import java.util.function.UnaryOperator;

public record Block(
        int index,
        List<String> labels,
        BlockType blockType,
        Position position,
        Direction direction,
        Configuration configuration) implements BlockPosition {

    public Block remap(UnaryOperator<Position> mapping) {
        return new Block(index, labels, blockType, mapping.apply(position), direction, configuration.remap(mapping));
    }

    public Block withConnections(PositionArray connections) {
        return new Block(index, labels, blockType, position, direction, connections);
    }
}
