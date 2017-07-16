package com.powerbridge.bssp.email.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.powerbridge.bssp.common.util.toolbox.DateUtil;
import com.powerbridge.bssp.email.entity.UserEmailMsg;
import com.powerbridge.bssp.email.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：EmailThread   
* 类描述： 多线层发送邮件业务逻辑层  
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public class EmailThread implements Runnable {

	@Autowired
	private MailService mailService;
	@Autowired
	private UserEmailMsg userEmailMsg;
	private int sumNum = 0;
	private List<String> list = new ArrayList<String>();
	
	public int getSumNum() {
		return sumNum;
	}

	public void setSumNum(int sumNum) {
		this.sumNum = sumNum;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	
    
	public EmailThread(List<String> list, UserEmailMsg userEmailMsg, MailService mailService) {
		this.list.addAll(list);
		this.userEmailMsg = userEmailMsg;
		this.mailService = mailService;
		sumNum += list.size();
	}

	@Override
	public void run() {
        try {
            //每10个邮箱批量发一次，发完休息1秒，直到发完为止
    		System.out.println(Thread.currentThread().getName());
                while(true){
                    if(list.size()>0){
                        List<String> list = queryList(10);
                    	System.out.println(Thread.currentThread().getName());
                        String[] arr = (String[])list.toArray(new String[list.size()]);
                    	System.out.println(arr);
                        for(String mail : arr){
                    		userEmailMsg.setToEmails(mail);
                        	System.out.println(Thread.currentThread().getName()+"邮件"+JSON.toJSON(mail));
                    		mailService.sendMail(userEmailMsg);
                        }
                        Thread.sleep(1000);
                    }else{
                        sumNum =0;
                        break;
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
    //获得要发送的list加锁
	public synchronized List<String> queryList(int num) {
		List<String> newList = new ArrayList<String>();
		if (list.size() <= num) {
			System.out.println("发送完成时间" + DateUtil.formatDateTime(new Date()));
			for (int i = 0; i < list.size(); i++) {
				newList.add(list.get(i));
			}
			list = new ArrayList<String>();
			System.out.println(JSON.toJSON(newList));
			return newList;
		} else {
			for (int i = 0; i < num; i++) {
				newList.add(list.get(i));
			}
			for (int i = 0; i < num; i++) {
				list.remove(0);
			}
		}
		return newList;
	}

}
