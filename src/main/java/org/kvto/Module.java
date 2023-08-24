package org.kvto;

import java.util.HashMap;

public abstract class Module {
    protected final SplittableUnit mainFunction;
    protected final HashMap<String, SplittableUnit> functions;

    Generator generator;

    Module(SplittableUnit mainFunction) {
        this.mainFunction = mainFunction;
        functions = new HashMap<>();
    }

    /**
     * implement of call mechanics
     * the return will replace the formal function body
     * generator will give some necessary information or method
     *
     * @param mode
     * @return
     */
    abstract String CallImpl(mode mode, Generator generator);

    /**
     * code before functions in main Processor
     *
     * @param generator
     * @return
     */
    abstract String GenMainFrame(Generator generator);
    // abstract String GenExternFrame(Generator generator);

    /**
     * code before functions in sub Processor
     * it needs to parse calls by CallImpl and jump to function
     *
     * @param generator
     * @return
     */
    abstract String GenSubFrame(Generator generator);

    /**
     * implement of return mechanics
     * it serves as a middle passer of returned value
     * generator will give some necessary information or method
     *
     * @param mode
     * @return
     */
    abstract String RetImpl(mode mode, Generator generator);


    Module addFunction(SplittableUnit splittableUnit) {
        functions.put(splittableUnit.identifier, splittableUnit);
        return this;
    }


}
