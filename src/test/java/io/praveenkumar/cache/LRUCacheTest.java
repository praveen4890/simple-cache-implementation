package io.praveenkumar.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {

    CacheConfig cacheConfig;
    CacheManager cacheManager;
    Cache cache;

    @Before
    public void init() throws Exception{
        cacheConfig = new CacheConfig(2, EvictionPolicy.LRU);
        cacheManager = new CacheManager();
        cache = cacheManager.createCache("myCache", cacheConfig);
    }

    @Test
    public void testClear() {
        cache.clear();
        Assert.assertEquals(cache.getAllItems(),"[]");
    }

    @Test
    public void testAutoEviction() {
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        Assert.assertEquals(cache.lookup("1"), null);
    }

    @Test
    public void testForecedEviction() {
        cache.put("1", "1");
        cache.put("2", "2");
        cache.evict("2");
        Assert.assertEquals(cache.getAllItems(),"[1:1]");
    }

    @Test
    public void testPut() {
        cache.put("1", "1");
        cache.put("2", "2");
        Assert.assertEquals(cache.getAllItems(), "[2:2,1:1]");
    }

    @Test
    public void testLookup() {
        cache.put("1", "1");
        cache.put("2", "2");
        Assert.assertEquals(cache.lookup("1"), "1");
    }

    @Test
    public void testGetName() {
        Assert.assertEquals(cache.getName(),"myCache");
    }

    @Test
    public void testGetAllItems() {
        cache.put("1", "1");
        cache.put("2", "2");
        Assert.assertEquals(cache.getAllItems(), "[2:2,1:1]");
    }
}