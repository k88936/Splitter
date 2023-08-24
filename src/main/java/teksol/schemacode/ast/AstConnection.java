package teksol.schemacode.ast;

import teksol.schemacode.mindustry.Position;
import teksol.schemacode.schema.SchematicsBuilder;

public record AstConnection(AstCoordinates position, String id) implements AstConfiguration {

    public AstConnection(AstCoordinates position) {
        this(position, null);
    }

    public AstConnection(int x, int y) {
        this(new AstCoordinates(x, y), null);
    }

    public AstConnection(int x, int y, boolean relative) {
        this(new AstCoordinates(x, y, relative), null);
    }

    public AstConnection(String id) {
        this(null, id);
    }

    public Position evaluate(SchematicsBuilder builder, Position lastPosition) {
        if (position != null) {
            return position.evaluate(builder, lastPosition);
        } else {
            return builder.getBlockPosition(id).position();
        }
    }
}
