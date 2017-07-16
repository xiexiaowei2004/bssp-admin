package com.powerbridge.bssp.base.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 *
 *
 * 项目名称：bssp Maven Webapp
 * 类名称：CaptchaImageCreateController
 * 类描述：验证码表示层
 * 创建人：simon.xie
 * 创建时间：2017年4月27日 下午10:12:17
 * 修改人：simon.xie
 * 修改时间：2017年4月27日 下午10:12:17
 * 修改备注：
 * @version
 *
 */
@Controller
public class CaptchaImageCreateController extends BaseController{

	@Autowired
	private Producer producer;

	@RequestMapping("/captchaMain-image")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String capText = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(capText);
		//保存到shiro session
//		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		//保存到
		Cookie cookie = new Cookie(Constants.KAPTCHA_SESSION_KEY,capText);
		cookie.setMaxAge(180);//3分钟
		response.addCookie(cookie);

		logger.info("captcha:"+capText);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}
}
