package teksol.schemacode.ast;

import teksol.schemacode.schema.SchematicsBuilder;

public interface AstText extends AstConfiguration {

    String getText(SchematicsBuilder builder);
}