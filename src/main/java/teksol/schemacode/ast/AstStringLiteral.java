package teksol.schemacode.ast;

import teksol.schemacode.SchematicsInternalError;
import teksol.schemacode.schema.SchematicsBuilder;

public record AstStringLiteral(String literal) implements AstText {

    public AstStringLiteral {
        if (literal != null && literal.isEmpty()) {
            throw new SchematicsInternalError("Empty literal.");
        }
    }

    public static AstStringLiteral fromText(String text) {
        return new AstStringLiteral('"' + text + '"');
    }

    @Override
    public String getText(SchematicsBuilder builder) {
        return getValue();
    }

    public String getValue() {
        // Strip quotes
        return literal.substring(1, literal.length() - 1);
    }
}
