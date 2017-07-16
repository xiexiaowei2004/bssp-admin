package com.powerbridge.bssp.common.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);  
    
    /** 
     * 初始化HttpClient 
     */  
    private CloseableHttpClient httpClient = HttpClients.createDefault();  
      
    /** 
     * POST方式调用 
     *  
     * @param url 
     * @param params 参数为NameValuePair键值对对象 
     * @return 响应字符串 
     * @throws java.io.UnsupportedEncodingException 
     */  
    public String executeByPOST(String url, List<NameValuePair> params) {  
        HttpPost post = new HttpPost(url);  
          
        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
        String responseJson = null;  
        try {  
            if (params != null) {  
                post.setEntity(new UrlEncodedFormEntity(params));  
            }  
            responseJson = httpClient.execute(post, responseHandler);  
            log.info("HttpClient POST请求结果：" + responseJson);  
        }  
        catch (ClientProtocolException e) {  
            e.printStackTrace();  
            log.info("HttpClient POST请求异常：" + e.getMessage());  
        }  
        catch (IOException e) {  
            e.printStackTrace();  
        }  
        finally {  
            httpClient.getConnectionManager().closeExpiredConnections();  
            httpClient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);  
        }  
        return responseJson;  
    }

    /**
     * 处理post请求.
     *
     * @param url    请求路径
     * @param params 参数
     * @return json
     */
    public String executeByPOST(String url, Map<String, String> params) throws Exception{
        //实例化httpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //实例化post方法
        HttpPost httpPost = new HttpPost(url);
        //处理参数
        List<NameValuePair> nvps = new ArrayList<>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
        //结果
        CloseableHttpResponse response = null;
        String responseJson = "";
        try {
            //提交的参数
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            //将参数给post方法
            httpPost.setEntity(uefEntity);
            //执行post方法
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                responseJson = EntityUtils.toString(response.getEntity(), "utf-8");
                log.info("HttpClient POST请求结果：" + responseJson);
            }
        } finally {
            httpClient.getConnectionManager().closeExpiredConnections();
            httpClient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);
        }
        return responseJson;
    }


    /** 
     * Get方式请求 
     *  
     * @param url 带参数占位符的URL，例：http://ip/User/user/center.aspx?_action=GetSimpleUserInfo&codes={0}&email={1} 
     * @param params 参数值数组，需要与url中占位符顺序对应 
     * @return 响应字符串 
     * @throws java.io.UnsupportedEncodingException 
     */  
    public String executeByGET(String url, Object[] params) {  
        String messages = MessageFormat.format(url, params);  
        HttpGet get = new HttpGet(messages);  
        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
        String responseJson = null;  
        try {  
            responseJson = httpClient.execute(get, responseHandler);  
            log.info("HttpClient GET请求结果：" + responseJson);  
        }  
        catch (ClientProtocolException e) {  
            e.printStackTrace();  
            log.error("HttpClient GET请求异常：" + e.getMessage());  
        }  
        catch (IOException e) {  
            e.printStackTrace();  
            log.error("HttpClient GET请求异常：" + e.getMessage());  
        }  
        finally {  
            httpClient.getConnectionManager().closeExpiredConnections();  
            httpClient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);  
        }  
        return responseJson;  
    }  
      
    /** 
     * @param url 
     * @return 
     */  
    public String executeByGET(String url) {  
        HttpGet get = new HttpGet(url);  
        ResponseHandler<String> responseHandler = new BasicResponseHandler();  
        String responseJson = null;  
        try {  
            responseJson = httpClient.execute(get, responseHandler);  
            log.info("HttpClient GET请求结果：" + responseJson);  
        }  
        catch (ClientProtocolException e) {  
            e.printStackTrace();  
            log.error("HttpClient GET请求异常：" + e.getMessage());  
        }  
        catch (IOException e) {  
            e.printStackTrace();  
            log.error("HttpClient GET请求异常：" + e.getMessage());  
        }  
        finally {  
            httpClient.getConnectionManager().closeExpiredConnections();  
            httpClient.getConnectionManager().closeIdleConnections(30, TimeUnit.SECONDS);  
        }  
        return responseJson;  
    } 
}
