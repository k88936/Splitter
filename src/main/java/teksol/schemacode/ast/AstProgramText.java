package teksol.schemacode.ast;

import teksol.schemacode.schema.SchematicsBuilder;

public record AstProgramText(AstText programText) implements AstProgram {

    @Override
    public String getProgramId(SchematicsBuilder builder) {
        return "embedded code";
    }

    @Override
    public String getProgramText(SchematicsBuilder builder) {
        return programText.getText(builder);
    }
}
