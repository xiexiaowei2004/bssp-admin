/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powerbridge.bssp.redis.service;

import redis.clients.jedis.ShardedJedis;

/**
 * 
 * @ClassName RedisDataSource
 * @Description redis数据源配置接口
 * @author Simon.xie
 * @Date 2017年5月18日 上午11:59:35
 * @version 1.0.0
 */
public interface IRedisDataSource {
    
    /**
     * 取得redis客户端，命令执行组件
     * @return 
     */
    public abstract ShardedJedis getRedisClient();
    
    /**
     * 将资源返还给pool
     * @param shardedJedis
     * @param broken 
     */
    public void returnResource(ShardedJedis shardedJedis, boolean broken);
}
