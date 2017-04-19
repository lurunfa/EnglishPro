package com.exam.http;
/*
 * 访问实例
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class HttpText extends Activity {

	protected static final int GET_BACKDATA = 0;
	String address = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		httpRequest();
	}

	private void httpRequest() {
		// TODO Auto-generated method stub
		// 都以POST请求网络，首先新建一个List<NameValuePair>
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		// 这是存放发送的字段，以键名和键值的方式。服务器以$name = $_POST["name"]接收
		pairs.add(new BasicNameValuePair("name", "123"));
		// 这是请求的构造方法，可以根据需要进行修改
		// 一共设置了四个参数，第一个是访问的地址，第二个是数据返回的接口，第三个是POST得数据集，第四个是对返回数据的编码
		httpUtil.sendHttpRequest(address, new HttpCallBackListener() {
			//这是返回数据的接口
			//用MESSAGE发送给Handler进行数据的处理
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				Message message = new Message();
				message.what = GET_BACKDATA;
				message.obj = response;
				handler.sendMessage(message);
			}
			//这是报错的处理
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				e.printStackTrace();
			}
		}, pairs, "utf-8");
	}
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			String response = (String) msg.obj;
			switch (msg.what) {
			case GET_BACKDATA:
				//这里是对返回信息的处理，其中msg.what表示放回信息的标志。msg.obj是承载返回信息
				break;

			default:
				break;
			}
			
		};
	};
}
