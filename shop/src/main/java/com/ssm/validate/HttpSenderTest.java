package com.ssm.validate;

import com.ssm.validate.HttpSender;

public class HttpSenderTest {
	public static void main(String[] args) {
		String url = "https://sms.253.com/msg/";// 应用地址
		String account = "13554081249";// 账号
		String pswd = "mxl13554081249";// 密码
		String mobile = "13554081249";// 手机号码，多个号码使用","分割
		String msg = "【恭喜您梅小龙先生】您好，您的验证码是123456,您获得了特等奖金额1000万!";// 短信内容
		boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
		String extno = null;// 扩展码

		try {
			String returnString = HttpSender.batchSend(url, account, pswd, mobile, msg, needstatus, extno);
			System.out.println("sucess :"+returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}
}
