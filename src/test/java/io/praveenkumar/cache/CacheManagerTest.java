package io.praveenkumar.cache;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheManagerTest {

    @Test
    public void testCacheCreation() {
        CacheConfig cacheConfig = new CacheConfig(2, EvictionPolicy.LRU);
        Cache cache = null;
        CacheManager cacheManager = new CacheManager();
        try {
            cache = cacheManager.createCache("myCache", cacheConfig);
        } catch (Exception e) {
            System.out.println("Error while creating cache");
            return;
        }

        Assert.assertEquals(cache.getName(), "myCache");
        Cache newCache = cacheManager.getCache("myCache");
        Assert.assertEquals(cache, newCache);
    }

    @Test(expected = Exception.class)
    public void testCacheCreationError() throws Exception{
        CacheConfig cacheConfig = new CacheConfig(2, EvictionPolicy.LRU);
        CacheManager cacheManager = new CacheManager();

        Cache cache = cacheManager.createCache("myCache", cacheConfig);
        Cache newCache = cacheManager.createCache("myCache", cacheConfig);
    }
}