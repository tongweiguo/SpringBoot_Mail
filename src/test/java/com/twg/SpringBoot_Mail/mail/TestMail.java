package com.twg.SpringBoot_Mail.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.twg.SpringBoot_Mail.mailService.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMail {

	@Autowired
	private MailService mailService;
	
	@Autowired
    private TemplateEngine templateEngine;

	@Test
	public void testSimpleMail(){

		mailService.sendSimpleMail("1449518863@qq.com", "主题:工资单", "你的工资已到账,10000元.");
	}

	@Test
	public void testHtmlMail() throws Exception {
		String content="<html>\n" +
				"<body>\n" +
				"    <h3>hello world ! 这是一封html邮件!</h3>\n" +
				"</body>\n" +
				"</html>";
		mailService.sendHtmlMail("1449518863@qq.com","主题:HTML邮件",content);
	}

	@Test
	public void sendAttachmentsMail() {
		String filePath="E:\\java面试考题.docx";
		mailService.sendAttachmentsMail("1449518863@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
	}
	
	@Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "E:\\图片\\011.jpg";

        mailService.sendInlineResourceMail("1449518863@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }
	
	@Test
	public void sendTemplateMail() {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable("id", "006");
	    String emailContent = templateEngine.process("email", context);

	    mailService.sendHtmlMail("1449518863@qq.com","主题：这是模板邮件",emailContent);
	}

}
