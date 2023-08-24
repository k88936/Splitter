package teksol.schemacode.ast;

import teksol.schemacode.mindustry.Position;
import teksol.schemacode.schema.SchematicsBuilder;
import teksol.schemacode.mindustry.ProcessorConfiguration;

import java.util.function.Consumer;
import java.util.regex.Pattern;

public record AstLinkPattern(String match) implements AstLink {

    @Override
    public void getProcessorLinks(Consumer<ProcessorConfiguration.Link> linkConsumer, SchematicsBuilder builder, Position processorPosition) {
        Pattern pattern = Pattern.compile(match.replace("*", ".*"));
        builder.getAstLabelMap().entrySet().stream()
                .filter(e -> !e.getKey().startsWith("#"))
                .filter(e -> pattern.matcher(e.getKey()).matches())
                .map(e -> new ProcessorConfiguration.Link(stripPrefix(e.getKey()), e.getValue().position()))
                .forEachOrdered(linkConsumer);
    }
}
