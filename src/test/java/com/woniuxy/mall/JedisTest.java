package com.woniuxy.mall;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void test1() {
        Jedis jedis = new Jedis("192.168.11.157", 6379);
        jedis.set("test1", "测试1");
        jedis.close();
    }
}
