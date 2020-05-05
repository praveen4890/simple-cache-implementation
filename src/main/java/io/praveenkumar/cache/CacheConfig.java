package io.praveenkumar.cache;

public class CacheConfig {

    private int cacheSize;
    private EvictionPolicy evictionPolicy;

    public int getCacheSize() {
        return cacheSize;
    }

    public EvictionPolicy getEvictionPolicy() {
        return evictionPolicy;
    }
}
