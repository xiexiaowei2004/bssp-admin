package com.powerbridge.bssp.email.service;

import javax.mail.MessagingException;

import com.powerbridge.bssp.email.entity.UserEmailMsg;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 
* 项目名称：bssp Maven Webapp
* 类名称：MailService   
* 类描述：邮箱发送业务逻辑层接口   
* 创建人：simon.xie
* 创建时间：2017年4月27日 下午10:12:17
* 修改人：simon.xie
* 修改时间：2017年4月27日 下午10:12:17
* @version
 */
public interface MailService {
	
	/**
	 * 邮件发送统一入口
	 * @param email
	 * 
	 */
	public void sendMail(UserEmailMsg userEmailMsg);
	
	/**
	 * 加入图片文件
	 * @param userEmailMsg
	 * @param helper
	 * @throws ServiceException
	 * @throws MessagingException
	 */
	public void setAddInline(UserEmailMsg userEmailMsg,MimeMessageHelper helper);
	
	/**
	 * 加入附件文件
	 * @param userEmailMsg
	 * @param helper
	 * @throws MessagingException
	 * @throws Exception
	 */
	public void setAddAttachment(UserEmailMsg userEmailMsg,MimeMessageHelper helper);

	/**
	 * 以velocity为模板发送邮件
	 * @param userEmailMsg
	 * @param helper
	 */
	public void sendWithTemplate(UserEmailMsg userEmailMsg,MimeMessageHelper helper);
}
