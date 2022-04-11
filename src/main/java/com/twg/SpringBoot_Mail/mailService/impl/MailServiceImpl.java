package com.twg.SpringBoot_Mail.mailService.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.twg.SpringBoot_Mail.mailService.MailService;

@Component
public class MailServiceImpl implements MailService{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.fromMail.addr}")
	private String fromMail;
	
	/**
	 * 发送文本邮件
	 * @param to 接收方
	 * @param title 标题
	 * @param content 内容
	 */
	public void sendSimpleMail(String to, String title, String content) {
		//创建邮件消息对象
		SimpleMailMessage smm = new SimpleMailMessage();
		//赋值谁发
		smm.setFrom(fromMail);
		//给谁发
		smm.setTo(to);
		//邮件主题	
		smm.setSubject(title);
		//邮件内容
		smm.setText(content);
		//开始发送
		try{
			mailSender.send(smm);
			log.info("简单邮件发送完成.");
		}catch (Exception e) {
			log.error("简单邮件发送失败:" + e);
		}
	}

	/**
	 * 发送html邮件
	 */
	public void sendHtmlMail(String to, String subject, String content) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();

		try {
			//true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromMail);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			mailSender.send(message);
			log.info("html邮件发送成功");
		} catch (MessagingException e) {
			log.error("发送html邮件时发生异常！", e);
		}
	}

	/**
	 * 发送带附件的邮件
	 */
	public void sendAttachmentsMail(String to, String subject, String content,
			String filePath) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromMail);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			//如果多个附件就多个add
			//helper.addAttachment("test"+fileName, file);

			mailSender.send(message);
			log.info("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			log.error("发送带附件的邮件时发生异常！", e);
		}
	}

	/**
	 * 发送正文中有静态资源（图片）的邮件
	 */
	public void sendInlineResourceMail(String to, String subject,
			String content, String rscPath, String rscId) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(fromMail);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			mailSender.send(message);
			log.info("嵌入静态资源的邮件已经发送。");
		} catch (MessagingException e) {
			log.error("发送嵌入静态资源的邮件时发生异常！", e);
		}
	}

}
