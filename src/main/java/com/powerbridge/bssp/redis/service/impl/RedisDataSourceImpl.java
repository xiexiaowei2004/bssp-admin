/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powerbridge.bssp.redis.service.impl;

import com.powerbridge.bssp.redis.service.IRedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * @ClassName RedisDataSourceImpl
 * @Description redis数据源配置实现
 * @author Simon.xie
 * @Date 2017年5月18日 上午10:35:17
 * @version 1.0.0
 */
@Service("redisDataSource")
public class RedisDataSourceImpl implements IRedisDataSource {

    private static final Logger log = LoggerFactory.getLogger(RedisDataSourceImpl.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public ShardedJedis getRedisClient() {
        try {
            ShardedJedis shardJedis = shardedJedisPool.getResource();
            return shardJedis;
        } catch (Exception e) {
            log.error("getRedisClient error", e);
        }
        return null;
    }

    @Override
    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
//        shardedJedisPool.returnResourceObject(shardedJedis);
        if (broken) {
            shardedJedisPool.returnResourceObject(shardedJedis);
        } else {
            shardedJedisPool.returnResource(shardedJedis);
        }
    }

}
