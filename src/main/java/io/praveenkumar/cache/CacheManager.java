package io.praveenkumar.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Cache manager takes care of creation of new caches
 * We can create new cache
 */
public class CacheManager {

    private Map<String, Cache> caches = new HashMap<>();

    public Cache createCache(String cacheName, CacheConfig config) throws Exception {
        if (caches.containsKey(cacheName)) {
            throw new Exception("Cache already exists with this cache name");
        }

        Cache cache = null;

        /**
         *
         */
        switch(config.getEvictionPolicy()) {
            case LRU:
                cache = new LRUCache(cacheName, config.getCacheSize());
                break;
        }

        caches.put(cacheName, cache);
        return cache;
    }

    public Cache getCache(String cacheName) {
        if(caches.containsKey(cacheName)) {
            return caches.get(cacheName);
        }
        return null;
    }

}
