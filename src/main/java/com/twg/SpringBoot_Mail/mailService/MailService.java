package com.twg.SpringBoot_Mail.mailService;

public interface MailService {
	
	/**
	 * <p>发送文本邮件</p>
	 * @param to 接收方
	 * @param title 标题
	 * @param content 内容
	 */
	public void sendSimpleMail(String to,String title,String content);
	
	/**
	 * <p>发送html格式邮件
	 * @param to 接收方
	 * @param subject 标题/主题
	 * @param content html格式内容
	 */
	public void sendHtmlMail(String to, String subject, String content);
	
	/** 
	 * <p>发送带附件的邮件
	 * @param to 接收方
	 * @param subject 标题/主题
	 * @param content 内容
	 * @param filePath 附件地址
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath);
	
	/**
	 * <p>发送带静态图片的邮件
	 * @param to 接收方
	 * @param subject 标题/主题
	 * @param content 内容
	 * @param rscPath 图片地址
	 * @param rscId 图片Id
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
