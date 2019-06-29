package com.hui.base.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <b><code>RedisTest</code></b>
 * <p/>
 * Description:
 * <p/>
 * <b>Creation Time:</b> 2019/6/29 13:59.
 *
 * @author HuWeihui
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * =============================== 基本操作 ===============================
     */

    @Test
    public void testGetValue() {
        String name = stringRedisTemplate.opsForValue().get("name");
        log.info(name);
    }


    @Test
    public void testSetValueAndExpire() {
        stringRedisTemplate.opsForValue().set("name", "huweihui", 10, TimeUnit.SECONDS);
    }


    @Test
    public void testSetValue() {
        stringRedisTemplate.opsForValue().set("name", "huweihui");
    }

    @Test
    public void testSetTimeOut() {
        stringRedisTemplate.expire("name", 10, TimeUnit.SECONDS);
    }


    @Test
    public void testIncrement() {
        stringRedisTemplate.opsForValue().set("val", "10");
        stringRedisTemplate.boundValueOps("name").increment(1);
    }


    @Test
    public void testGetExpire() {
        Long expire = stringRedisTemplate.getExpire("name", TimeUnit.SECONDS);
        log.info(expire.toString());

    }

    @Test
    public void testHashKey() {
        Boolean result = stringRedisTemplate.hasKey("name");
        log.info(result.toString());
    }


    /**
     * =============================== Set操作 ===============================
     */

    @Test
    public void testGetAndAdd() {
        stringRedisTemplate.opsForSet().add("set-name", "name-1");
        stringRedisTemplate.opsForSet().add("set-name", "name-2");

        Set<String> name = stringRedisTemplate.opsForSet().members("set-name");

        for (String s : name) {
            log.info(s);
        }

    }


    @Test
    public void testSetExpire() {
        stringRedisTemplate.expire("set-name", 100, TimeUnit.SECONDS);

        Long expire = stringRedisTemplate.getExpire("set-name", TimeUnit.SECONDS);

        log.info("expire time : {}", expire);
    }


    /**
     * =============================== ZSet(有序的set)操作 ===============================
     */


    /**
     * =============================== List操作 ===============================
     */

    @Test
    public void testList() {

        List<String> list1 = new ArrayList<String>();
        list1.add("a1");
        list1.add("a2");
        list1.add("a3");

        List<String> list2 = new ArrayList<String>();
        list2.add("b1");
        list2.add("b2");
        list2.add("b3");
        stringRedisTemplate.opsForList().leftPushAll("listkey1", list1);
        stringRedisTemplate.opsForList().rightPushAll("listkey2", list2);
        String listkey1 = stringRedisTemplate.opsForList().leftPop("listkey1");
        String listkey2 = stringRedisTemplate.opsForList().rightPop("listkey2");
        log.info("listkey1: {}", listkey1);
        log.info("listkey2: {}", listkey2);
    }

    /**
     * =============================== Map操作 ===============================
     */

    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");
        map.put("key5", "value5");
        stringRedisTemplate.opsForHash().putAll("map1", map);
        Map<Object, Object> resultMap = stringRedisTemplate.opsForHash().entries("map1");
        List<Object> reslutMapList = stringRedisTemplate.opsForHash().values("map1");
        Set<Object> resultMapSet = stringRedisTemplate.opsForHash().keys("map1");
        String value = (String) stringRedisTemplate.opsForHash().get("map1", "key1");
        log.info("value:{}",  value);
        log.info("resultMapSet: {}" , resultMapSet);
        log.info("resultMap:{}" , resultMap);
        log.info("resulreslutMapListtMap:{}" , reslutMapList);
    }

}
