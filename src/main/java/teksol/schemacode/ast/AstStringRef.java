package teksol.schemacode.ast;

import teksol.schemacode.schema.SchematicsBuilder;

public record AstStringRef(String reference) implements AstText {

    @Override
    public String getText(SchematicsBuilder builder) {
        return builder.getText(reference);
    }
}
