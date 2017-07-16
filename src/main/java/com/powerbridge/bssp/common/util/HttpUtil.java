package com.powerbridge.bssp.common.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {
	protected static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	/**
	 * http post请求
	 * @param url						地址
	 * @param postContent				post内容格式为param1=value&param2=value2&param3=value3
	 * @return
	 * @throws IOException
	 */
	public static String httpPostRequest(URL url, String postContent) throws Exception{
		OutputStream outputstream = null;
		BufferedReader in = null;
		try
		{
			URLConnection httpurlconnection = url.openConnection();
			httpurlconnection.setConnectTimeout(10 * 1000);
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setUseCaches(false);
			OutputStreamWriter out = new OutputStreamWriter(httpurlconnection
					.getOutputStream(), "UTF-8");
			out.write(postContent);
			out.flush();
			
			StringBuffer result = new StringBuffer();
			in = new BufferedReader(new InputStreamReader(httpurlconnection
					.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null)
			{
				result.append(line);
			}
			return result.toString();
		}
		catch(Exception ex){
			//logger.error("post请求异常：" + ex.getMessage());
			throw new Exception("post请求异常：" + ex.getMessage());
		}
		finally
		{
			if (outputstream != null)
			{
				try
				{
					outputstream.close();
				}
				catch (IOException e)
				{
					outputstream = null;
				}
			}
			if (in != null)
			{
				try
				{
					in.close();
				}
				catch (IOException e)
				{
					in = null;
				}
			}
		}	
	}	
	
	/**
	 * 通过httpClient进行post提交
	 * @param url				提交url地址
	 * @param charset			字符集
	 * @param keys				参数名
	 * @param values			参数值
	 * @return
	 * @throws Exception
	 */
	public static String HttpClientPost(String url , String charset , String[] keys , String[] values) throws Exception{
		HttpClient client = null;
		PostMethod post = null;
		String result = "";
		int status = 200;
		try {
	           client = new HttpClient();                
               //PostMethod对象用于存放地址
             //总账户的测试方法
               post = new PostMethod(url);         
               //NameValuePair数组对象用于传入参数
               post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=" + charset);//在头文件中设置转码

               String key = "";
               String value = "";
               NameValuePair temp = null;
               NameValuePair[] params = new NameValuePair[keys.length];
               for (int i = 0; i < keys.length; i++) {
            	   key = (String)keys[i];
            	   value = (String)values[i];
            	   temp = new NameValuePair(key , value);   
            	   params[i] = temp;
            	   temp = null;
               }
              post.setRequestBody(params); 
               //执行的状态
              status = client.executeMethod(post); 
              logger.info("status = " + status);
               
              if(status == 200){
            	  result = post.getResponseBodyAsString();
              }
               
		} catch (Exception ex) {
			// TODO: handle exception
			throw new Exception("通过httpClient进行post提交异常：" + ex.getMessage() + " status = " + status);
		}
		finally{
			post.releaseConnection(); 
		}
		return result;
	}
	
	/**
	 * 字符串处理,如果输入字符串为null则返回"",否则返回本字符串去前后空格。
	 * @param inputStr			输入字符串
	 * @return	string 			输出字符串
	 */
    public static String doString(String inputStr){
    	//如果为null返回""
        if(inputStr == null || "".equals(inputStr) || "null".equals(inputStr)){
    		return "";
    	}	
        //否则返回本字符串把前后空格去掉
    	return inputStr.trim();
    }

    /**
     * 对象处理，如果输入对象为null返回"",否则则返回本字符对象信息，去掉前后空格
     * @param object
     * @return
     */
    public static String doString(Object object){
    	//如果为null返回""
        if(object == null || "null".equals(object) || "".equals(object)){
    		return "";
    	}	
        //否则返回本字符串把前后空格去掉
    	return object.toString().trim();
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
