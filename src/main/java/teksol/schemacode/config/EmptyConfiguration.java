package teksol.schemacode.config;

import teksol.schemacode.mindustry.ConfigurationType;

public enum EmptyConfiguration implements Configuration {
    EMPTY;

    @Override
    public <T extends Configuration> T as(Class<T> type) {
        return ConfigurationType.createEmpty(type);
    }
}
