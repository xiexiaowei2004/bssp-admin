/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powerbridge.bssp.redis.service.impl;

import com.powerbridge.bssp.common.util.SerializableUtil;
import com.powerbridge.bssp.redis.service.IRedisClientTemplate;
import com.powerbridge.bssp.redis.service.IRedisDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @ClassName RedisClientTemplateImpl
 * @Description redis方法工具实现类
 * @author Simon.xie
 * @Date 2017年5月18日 上午10:35:33
 * @version 1.0.0
 */
//@Repository("redisClientTemplate")
@Service("redisClientTemplate")
public class RedisClientTemplateImpl implements IRedisClientTemplate {

    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplateImpl.class);

    @Autowired
    private IRedisDataSource redisDataSource;

    public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }

    /**
     * 设置单个值，指定时间点过期
     *
     * @param key
     * @param value
     * @param unixTime <=0将忽略过期时间 
     * @return
     */
    public String set(String key, Object value, Long unixTime) {
        if(!(value instanceof Serializable)){
            throw new RuntimeException("Cann't set value into redis if not implements Serializable!");
        }
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key.getBytes(), SerializableUtil.serialize(value));
            if (unixTime != null && unixTime > 0) {
                shardedJedis.expireAt(key, unixTime);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
            shardedJedis.disconnect();
        }
        return result;
    }

    /**
     * 设置值，指定时间后过期
     *
     * @param key
     * @param value
     * @param seconds <=0将忽略过期时间 
     * @return
     */
    public String set(String key, Object value, Integer seconds) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key.getBytes(), SerializableUtil.serialize(value));
            if (seconds != null && seconds > 0) {
                shardedJedis.expire(key, seconds);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
            shardedJedis.disconnect();
        }
        return result;
    }

    /**
     * 更新过期时间
     * @param key
     * @param seconds
     * @return 
     */
    public Long expire(String key, Integer seconds) {
        if (seconds == null || seconds <= 0) {
            return 0L;
        } else {
            Long result = 0L;
            ShardedJedis shardedJedis = redisDataSource.getRedisClient();
            if (shardedJedis == null) {
                return result;
            }
            boolean broken = false;
            try {
                shardedJedis.expire(key, seconds);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                broken = true;
            } finally {
                redisDataSource.returnResource(shardedJedis, broken);
            shardedJedis.disconnect();
            }
            return result;
        }
    }

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        Object result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            byte[] bytes = shardedJedis.get(key.getBytes());
            if(bytes != null && bytes.length > 0){
                result = SerializableUtil.unserialize(bytes);
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
            shardedJedis.disconnect();
        }
        return result;
    }

    /**
     * 判读key值是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.exists(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
            shardedJedis.disconnect();
        }
        return result;
    }

    /**
     * 获取key对应value的类型
     *
     * @param key
     * @return
     */
    public String type(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.type(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
            shardedJedis.disconnect();
        }
        return result;
    }

    /**
     * 删除某个key
     *
     * @param key
     * @return
     */
    public Long delete(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.del(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
            shardedJedis.disconnect();
        }
        return result;
    }
}
