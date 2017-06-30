package com.ssm.validate;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/** 中国网建SMS短信通
 *  http://sms.webchinese.cn/user/
 * @author meixl
 * mxl_shop
 * mxl13554081249   key : c729efd30e6dcf1d59e3
 */
public class SMS {
	
	public static void main(String[] args) throws Exception {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "mxl_shop"), new NameValuePair("Key", "c729efd30e6dcf1d59e3"),
				new NameValuePair("smsMob", "13554081249"), new NameValuePair("smsText", "验证码：www888") };
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();

	}

}