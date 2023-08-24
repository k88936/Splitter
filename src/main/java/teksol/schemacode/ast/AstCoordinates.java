package teksol.schemacode.ast;

import teksol.schemacode.mindustry.Position;
import teksol.schemacode.schema.SchematicsBuilder;

public record AstCoordinates(Position coordinates, boolean relative, String relativeTo) implements AstSchemaItem {

    public AstCoordinates(int x, int y, String relativeTo) {
        this(new Position(x, y), true, relativeTo);
    }

    public AstCoordinates(int x, int y) {
        this(new Position(x, y), false, null);
    }

    public AstCoordinates(int x, int y, boolean relative) {
        this(new Position(x, y), relative, null);
    }

    public Position evaluate(SchematicsBuilder builder, Position lastPosition) {
        if (relative) {
            Position rel = relativeTo == null ? lastPosition : builder.getBlockPosition(relativeTo).position();
            return rel.add(coordinates);
        } else {
            return builder.getAnchor(coordinates());
        }
    }

    public Position coordinates() {
        return coordinates;
    }

    public String getRelativeTo() {
        return relativeTo;
    }

    public AstCoordinates relative(boolean negate) {
        return negate ? new AstCoordinates(-getX(), -getY(), true) : new AstCoordinates(getX(), getY(), true);
    }

    public int getX() {
        return coordinates.x();
    }

    public int getY() {
        return coordinates.y();
    }

    public AstCoordinates relativeTo(String id) {
        return new AstCoordinates(getX(), getY(), id);
    }
}
