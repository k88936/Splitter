package teksol.schemacode.ast;

import teksol.schemacode.mindustry.Position;
import teksol.schemacode.schema.SchematicsBuilder;
import teksol.schemacode.mindustry.ProcessorConfiguration;

import java.util.function.Consumer;

public record AstLinkPos(AstConnection connection, String name, boolean virtual) implements AstLink {

    @Override
    public void getProcessorLinks(Consumer<ProcessorConfiguration.Link> linkConsumer, SchematicsBuilder builder, Position processorPosition) {
        linkConsumer.accept(new ProcessorConfiguration.Link(stripPrefix(trueLinkName()), connection.evaluate(builder, processorPosition)));
    }

    private String trueLinkName() {
        return name == null ? connection().id() : name;
    }
}
