package Test;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 发送邮件的测试程序
 * 发件邮箱需要开启 pop3/smtp  密码为短信验证码
 * 链接 -> http://blog.csdn.net/h348592532/article/details/23254701
 */
public class MailTest {

	public static void main(String[] args) throws MessagingException {
		send();
	}

	public static void send() throws MessagingException {
		String host = "smtp.qq.com";
		int port = 25;
		String username = "694318499@qq.com";
		String password = "figagtmyfunabdec";

		String content = "<a href='http://www.baidu.com'>测试的HTML邮件</a>";

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		mailSender.setJavaMailProperties(javaMailProperties);
		String email = "3172870837@qq.com";
		String tousername = "yyyy";

		MimeMessage message = mailSender.createMimeMessage();
		// message.setContent("<a href='http://www.baidu.com'>测试的HTML邮件</a>","text/html;charset=UTF-8");

		MimeMessageHelper help = new MimeMessageHelper(message, true, "UTF-8");
		help.setFrom(username);
		help.setTo(new String[] { email });
		help.setSubject("邮件来了");
		Date date = new Date(2016, 11, 11);
		// String _content = content.replace("${date}", date.toLocaleString());
		// _content = content.replace("${tousername}", tousername);
		help.setText(content, true);
		
		for (int i = 0; i < 3; i++) {
			mailSender.send(message);
		}
	}
}
