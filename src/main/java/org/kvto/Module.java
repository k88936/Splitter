package org.kvto;

import java.util.HashMap;

public abstract class Module {
    protected final SplittableUnit mainFunction;
    protected final HashMap<String, SplittableUnit> functions;

    Module(SplittableUnit mainFunction) {
        this.mainFunction = mainFunction;
        functions = new HashMap<>();
    }

    abstract String CallImpl(mode mode);

    abstract String GenExternFrame(Generator generator);

    abstract String GenMainFrame(Generator generator);

    abstract String GenSubFrame(Generator generator);

    abstract String RetImpl(mode mode);

    Module addFunction(SplittableUnit splittableUnit) {
        functions.put(splittableUnit.identifier, splittableUnit);
        return this;
    }


}
