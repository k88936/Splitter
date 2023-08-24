package teksol.schemacode.config;

import teksol.schemacode.IOConsumer;
import teksol.schemacode.IOSupplier;
import teksol.schemacode.mindustry.Position;
import teksol.schemacode.schema.Block;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public record PositionArray(List<Position> positions) implements Configuration {

    public static final PositionArray EMPTY = new PositionArray(List.of());

    public PositionArray(Position... positions) {
        this(List.of(positions));
    }

    public PositionArray(List<Position> positions) {
        this.positions = positions.stream().sorted().toList();
    }

    public static PositionArray create(int length, IOSupplier<Position> supplier) throws IOException {
        List<Position> array = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            array.add(supplier.get());
        }
        return new PositionArray(List.copyOf(array));
    }

    @Override
    public <T extends Configuration> T as(Class<T> type) {
        if (type == Position.class) {
            return type.cast(positions.isEmpty() ? Position.INVALID : positions.get(0));
        }
        return Configuration.super.as(type);
    }

    @Override
    public Configuration encode(Block block) {
        return remap(p -> p.sub(block.position()));
    }

    @Override
    public PositionArray remap(UnaryOperator<Position> transformation) {
        return new PositionArray(positions.stream().map(transformation).toList());
    }

    public Position get(int index) {
        return positions.get(index);
    }

    public int size() {
        return positions.size();
    }

    public void store(IOConsumer<Position> consumer) throws IOException {
        for (Position position : positions) {
            consumer.accept(position);
        }
    }
}
