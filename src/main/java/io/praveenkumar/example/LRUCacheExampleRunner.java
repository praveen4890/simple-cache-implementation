package io.praveenkumar.example;

import io.praveenkumar.cache.Cache;
import io.praveenkumar.cache.CacheConfig;
import io.praveenkumar.cache.CacheManager;
import io.praveenkumar.cache.EvictionPolicy;

/**
 * Sample class to demonstrate the LRU Cache Implementation
 */
public class LRUCacheExampleRunner {
    public static void main(String[] args) {

        // Create cache config with LRU Policy
        CacheConfig cacheConfig = new CacheConfig( 5, EvictionPolicy.LRU);
        Cache lruCache = null;

        try {
            // Create cache object
            lruCache = new CacheManager().createCache("myCache", cacheConfig);
        } catch (Exception e) {
            System.out.println(String.format("Error while creating cache %s", e.getMessage()));
            return;
        }

        // Insert 10 objects into cache
        for(int i = 1; i <= 5; i++) {
            lruCache.put(String.format("K%d",  i), String.format("V%d",  i));
        }

        System.out.println("### printing cache ###");
        System.out.println(lruCache.getAllItems());

        lruCache.lookup("K1");
        System.out.println("### printing cache after accessing K1 ###");
        System.out.println(lruCache.getAllItems());

        lruCache.put(String.format("KX"), String.format("VX"));

        System.out.println("### printing cache after adding new objects ###");
        System.out.println(lruCache.getAllItems());
    }
}
