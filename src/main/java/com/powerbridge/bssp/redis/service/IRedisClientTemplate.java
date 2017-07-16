/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powerbridge.bssp.redis.service;

/**
 * 
 * @ClassName RedisClientTemplate
 * @Description redis方法工具类接口
 * @author Simon.xie
 * @Date 2017年5月18日 上午11:59:49
 * @version 1.0.0
 */
public interface IRedisClientTemplate {

    public void disconnect();

    /**
     * 设置单个值，指定时间点过期
     *
     * @param key
     * @param value
     * @param unixTime <=0将忽略过期时间 
     * @return
     */
    public String set(String key, Object value, Long unixTime);

    /**
     * 设置值，指定时间后过期
     *
     * @param key
     * @param value
     * @param seconds <=0将忽略过期时间 
     * @return
     */
    public String set(String key, Object value, Integer seconds);

    /**
     * 更新过期时间
     * @param key
     * @param seconds
     * @return 
     */
    public Long expire(String key, Integer seconds);

    /**
     * 获取单个值
     *
     * @param key
     * @return
     */
    public Object get(String key);

    /**
     * 判读key值是否存在
     *
     * @param key
     * @return
     */
    public Boolean exists(String key);

    /**
     * 获取key对应value的类型
     *
     * @param key
     * @return
     */
    public String type(String key);

    /**
     * 删除某个key
     *
     * @param key
     * @return
     */
    public Long delete(String key);
    
}
