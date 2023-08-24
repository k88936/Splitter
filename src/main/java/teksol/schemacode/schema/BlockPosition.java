package teksol.schemacode.schema;

import teksol.schemacode.config.Configuration;
import teksol.schemacode.mimex.BlockType;
import teksol.schemacode.mindustry.ConfigurationType;
import teksol.schemacode.mindustry.Implementation;
import teksol.schemacode.mindustry.Position;

public interface BlockPosition {
    default String area() {
        return size() == 1 ? "(%d, %d)".formatted(x(), y()) : "(%d, %d) - (%d, %d)".formatted(x(), y(), xMax(), yMax());
    }

    default Class<? extends Configuration> configurationClass() {
        return blockType().implementation().configurationClass();
    }

    default ConfigurationType configurationType() {
        return blockType().configurationType();
    }

    default Implementation implementation() {
        return blockType().implementation();
    }

    int index();

    default String name() {
        return blockType().name();
    }

    BlockType blockType();

    Position position();

    default int size() {
        return blockType().size();
    }

    default int x() {
        return position().x();
    }

    default int xMax() {
        return position().x() + size() - 1;
    }

    default int y() {
        return position().y();
    }

    default int yMax() {
        return position().y() + size() - 1;
    }
}
