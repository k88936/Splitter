package teksol.schemacode.ast;

import teksol.schemacode.schema.Language;

import java.util.List;

public record AstProcessor(List<AstLink> links, AstProgram program, Language language) implements AstConfiguration {
}
