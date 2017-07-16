/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.powerbridge.bssp.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName RedisSession
 * @Description 存储redis的Session包装类
 * @author Simon.xie
 * @Date 2017年5月18日 上午11:59:25
 * @version 1.0.0
 */
public class IRedisSession implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private final Logger logger = LoggerFactory.getLogger(IRedisSession.class);
    
    private static final String[] attributes = {"adminUser",
        "employee",
        "currentUser",
        "orderVO",
        "orderEntryVO",
        "wechat_user",
        "inside_sell_wechat_user",
        "website",
        "accessToken",
        "account",
        "CAPTCHA",
        "CURR_WEBSITE",
        "INSIDESELL_RETURN_URI",
        "RETURN_URI",
        "token",
        "navigations",
        "comboToken",
        "MOBILECAPTCHA",
        "spcartHolder",
        "loginType"
    };
    
    public static final String KEY_PREFIX = "SESSION_WELFARE_";
    public static final String KEY_ADMIN_PREFIX = "SESSION_ADMIN_";
    
    private Map<String, Object> attribute;
    private transient HttpSession session;
    
    public IRedisSession(){
        attribute = new HashMap<>();
    }
    
    public IRedisSession(HttpSession session){
        this.session = session;
    }
    
    public IRedisSession readSession(){
        Enumeration<String> attNames = session.getAttributeNames();
        attribute = new HashMap<>();
        while (attNames.hasMoreElements()) {
            String nextElement = attNames.nextElement();
            for(String attName : attributes){
                if(attName.equals(nextElement)){
                    Object value = session.getAttribute(nextElement);
                    attribute.put(nextElement, value);
                    break;
                }
            }
        }
        return this;
    }
    
    /**
     * 写session
     * @param session
     * @return 
     */
    public HttpSession writeSession(HttpSession session){
        if(session == null){
            return null;
        }else{
            attribute.entrySet().stream().forEach((entry) -> {
                for(String attName : attributes){
                    if(attName.equals(entry.getKey())){
                        session.setAttribute(entry.getKey(), entry.getValue());
                        break;
                    }
                }
            });
        }
        return session;
    }
    
    public Object get(String key){
        return attribute.get(key);
    }
    
    public void set(String key, Object value){
        attribute.put(key, value);
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, Object> attribute) {
        this.attribute = attribute;
    }
    
}
