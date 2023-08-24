package teksol.schemacode.ast;

import teksol.schemacode.schema.SchematicsBuilder;

public interface AstProgram extends AstSchemaItem {

    String getProgramId(SchematicsBuilder builder);

    String getProgramText(SchematicsBuilder builder);
}
