package com.exam.http;
/*
 * 网络请求的封装
 * 例子
 * 
 */
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public class httpUtil {
	public static  void sendHttpRequest(
			final String address, final HttpCallBackListener listener,final List<NameValuePair> pairs,final String encode) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient client = null;
				try {
					client = new DefaultHttpClient();
					//HttpPost httpPost = new HttpPost("http://192.168.56.1/"+adresss);
					HttpPost httpPost = new HttpPost(address);
					httpPost.setEntity(new UrlEncodedFormEntity(pairs,"utf-8"));
					HttpResponse httpResponse = client.execute(httpPost);
					String response = "";
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						response = EntityUtils.toString(httpResponse.getEntity(),encode);
					}
					System.out.println(response);
					if (listener != null) {
						listener.onFinish(response);
					}
				} catch (Exception e) {
					// TODO: handle exception
					if (listener != null) {
						listener.onError(e);
					}
				}
//				finally{
//					client.getConnectionManager().shutdown();
//				}
				
			}
		}).start();
	}
}
