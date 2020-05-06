package io.praveenkumar.cache;

/**
 * Class to store properties related to Cache
 */
public class CacheConfig {

    /**
     * Size of LRU Cache
     */
    private int cacheSize;

    /**
     * Property to define the Eviction policy of the cache
     */
    private EvictionPolicy evictionPolicy;

    public CacheConfig(int cacheSize, EvictionPolicy evictionPolicy) {
        this.cacheSize = cacheSize;
        this.evictionPolicy = evictionPolicy;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public EvictionPolicy getEvictionPolicy() {
        return evictionPolicy;
    }


}
