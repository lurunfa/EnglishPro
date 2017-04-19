//学生登录窗口，To：StudentMain
package com.bupt.english.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLogin extends Activity implements OnClickListener{
	private EditText accountEdit, passEdit;
	private Button login;
	private String usename;
	public static final int SHOW_RESPONSE = 0;
	AppClass getpath = new AppClass();
	//String path = getpath.getpath()+"test1.php";
	String path = getpath.getpath()+"english/login/login.php";
	//处理网络访问接收的信息
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				if (response.equals("success")) {
					Toast.makeText(StudentLogin.this, "用户登录成功！", Toast.LENGTH_SHORT)
							.show();

					Intent intent = new Intent(StudentLogin.this, StudentMain.class);
					intent.putExtra("ID", usename);
					startActivity(intent);
					finish();
				} else {

					Toast.makeText(StudentLogin.this, "用户验证失败！", Toast.LENGTH_SHORT)
							.show();
				}
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentlogin);
		accountEdit = (EditText) findViewById(R.id.account_edit);
		passEdit = (EditText) findViewById(R.id.password_edit);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(this);
		accountEdit.setText("2012211934");
		passEdit.setText("111111");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if (v.getId() == R.id.login) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// 获取输入的账户密码。以Httpclient请求服务器。
					usename = accountEdit.getText().toString();
					String password = passEdit.getText().toString();
					HttpClient mHttpClient = new DefaultHttpClient();
					mHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);
					HttpPost mPost = new HttpPost(path);
					//发送得信息
					List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
					pairs.add(new BasicNameValuePair("username", usename));
					pairs.add(new BasicNameValuePair("password", password));
					pairs.add(new BasicNameValuePair("usertype", "1"));
					System.out.println(pairs.toString());
					try {
						mPost.setEntity(new UrlEncodedFormEntity(pairs, "utf-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						
						HttpResponse httpResponse = mHttpClient.execute(mPost);
						
						int res = httpResponse.getStatusLine().getStatusCode();
						if (res == 200) {
							HttpEntity entity = httpResponse.getEntity();
							String response = EntityUtils.toString(entity,
									"utf-8");
							
							Message message = new Message();
							message.what = SHOW_RESPONSE;
							message.obj = parseJSON(response);
							handler.sendMessage(message);

						}else {
							Toast.makeText(StudentLogin.this, "连接超时", Toast.LENGTH_SHORT).show();
						}

					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				//对JSON数据进行解释
				private String parseJSON(String response) {
					JSONObject jsonObject = null;
					// flag为登录成功与否的标记,从服务器端返回的数据
					String flag = "";
					try {
						jsonObject = new JSONObject(response);
						flag = jsonObject.getString("flag");

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return flag;

				}

			}).start();
			
		}
	}

	

}
