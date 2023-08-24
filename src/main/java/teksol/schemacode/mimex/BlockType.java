package teksol.schemacode.mimex;

import teksol.schemacode.mindustry.ConfigurationType;
import teksol.schemacode.mindustry.Implementation;

import java.util.List;
import java.util.Map;

public record BlockType(
        String name,
        Implementation implementation,
        int size,
        boolean hasPower,
        boolean configurable,
        String category,
        float range,
        int maxNodes,
        boolean rotate,
        List<String> unitPlans
) {

    private static final Map<String, BlockType> NAME_MAP = BlockTypeReader.createFromResource();

    public static BlockType forName(String name) {
        return NAME_MAP.get(name);
    }

    public static boolean isNameValid(String name) {
        return NAME_MAP.containsKey(name);
    }

    public ConfigurationType configurationType() {
        return implementation.configurationType();
    }

    public String getBaseLinkName() {
        String strippedTypeName = name.substring(1);
        if (strippedTypeName.contains("-")) {
            String[] s = strippedTypeName.split("-");
            //filter out 'large' and numbers at the end of block names
            if (s.length >= 2 && (s[s.length - 1].equals("large") || s[s.length - 1].matches("\\d+"))) {
                return s[s.length - 2];
            } else {
                return s[s.length - 1];
            }
        }
        return strippedTypeName;
    }
}
