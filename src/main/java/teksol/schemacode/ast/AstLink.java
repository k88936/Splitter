package teksol.schemacode.ast;

import teksol.schemacode.mindustry.Position;
import teksol.schemacode.schema.SchematicsBuilder;
import teksol.schemacode.mindustry.ProcessorConfiguration;

import java.util.function.Consumer;

public interface AstLink extends AstSchemaItem {

    void getProcessorLinks(Consumer<ProcessorConfiguration.Link> linkConsumer, SchematicsBuilder builder, Position processorPosition);

    default String stripPrefix(String linkName) {
        int pos = linkName.indexOf("-");
        return pos < 0 ? linkName : linkName.substring(pos + 1);
    }
}
