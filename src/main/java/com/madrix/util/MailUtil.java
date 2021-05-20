package com.madrix.util;

import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.madrix.service.impl.RemoteInfoSynServiceImpl;
import com.madrix.util.UUID;
import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil {

	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
	// 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	public static String myEmailAccount = "support@epoweron.com";
	public static String myEmailPassword = "XGdie264";

	// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
	// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
	public static String myEmailSMTPHost = "smtp.office365.com";

	/**
	 * 发送邮件方法
	 * 
	 * @param receiveMailAccount
	 *            接收用户邮箱
	 * @return
	 * @data 2017年9月16日
	 * @author sdc
	 * @throws GeneralSecurityException
	 */
	public static String sendMail(String receiveMailAccount, String msg,String title) throws GeneralSecurityException {
		if ("0".equals(RemoteInfoSynServiceImpl.sendEmail)) {
			return "success";
		}
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties props = new Properties(); // 参数配置
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP
																// 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

//		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
//		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
//		props.put("mail.smtp.socketFactory.fallback", "false");
//		props.put("mail.smtp.socketFactory.port", "587");
//		props.put("mail.smtp.port", "587"); // google使用465或587端口
//		MailSSLSocketFactory sf = new MailSSLSocketFactory();
//		sf.setTrustAllHosts(true);
//		props.put("mail.smtp.ssl.socketFactory", sf);

		props.put("mail.smtp.starttls.enable", "true");

		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

		try {
			MimeMessage message = null;
			message = MailMessageUtil.createMimeMessage(session,myEmailAccount,receiveMailAccount,msg,title);
			// 当返回的信息为空时表示没有该用户 返回noUserError错误
			if (message == null) {
				return "noUserError";
			}
			// 4. 根据 Session 获取邮件传输对象
			Transport transport = session.getTransport();

			// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
			//
			// PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
			// 仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
			// 类型到对应邮件服务器的帮助网站上查看具体失败原因。
			//
			// PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
			// (1) 邮箱没有开启 SMTP 服务;
			// (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
			// (3) 邮箱服务器要求必须要使用 SSL 安全连接;
			// (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
			// (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
			//
			// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
			transport.connect(myEmailAccount, myEmailPassword);

			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
			// 获取到的是在创建邮件对象时添加的所有收件人,
			// 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());

			// 7. 关闭连接
			transport.close();

			return "success";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}

	public static void main(String[] args) throws GeneralSecurityException {
		sendMail("1536242521@qq.com","csd","123");
	}
}