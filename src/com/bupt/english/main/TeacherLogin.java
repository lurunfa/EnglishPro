package com.bupt.english.main;
/*
 * 教师登录页面
 */
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.bupt.english.main.R;

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

public class TeacherLogin extends Activity implements OnClickListener {
	
	private EditText accountEdit, passEdit;
	private Button login;
	private HashMap<String, String> session = new HashMap<String, String>();
	private String usename;
	public static final int SHOW_RESPONSE = 0;
	AppClass getpath = new AppClass();
	//String path = getpath.getpath()+"test1.php";
	String path = getpath.getpath()+"english/login/login.php";
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				System.out.println(response);
				if (response.equals("success")) {
					Toast.makeText(TeacherLogin.this, "用户登录成功！", Toast.LENGTH_SHORT)
							.show();
					Intent intent = new Intent(TeacherLogin.this, TeacherMain.class);
					Bundle map = new Bundle();
					map.putSerializable("sessionid", session);
					intent.putExtra("session", map);
					intent.putExtra("ID", usename);
					startActivity(intent);
					finish();
				} else {

					Toast.makeText(TeacherLogin.this, "用户验证失败！", Toast.LENGTH_SHORT)
							.show();
				}
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacherlogin);
		accountEdit = (EditText) findViewById(R.id.account_edit);
		passEdit = (EditText) findViewById(R.id.password_edit);
		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(this);
		accountEdit.setText("000005");
		passEdit.setText("123456");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		if (v.getId() == R.id.login) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(path);
					usename = accountEdit.getText().toString();
					String password = passEdit.getText().toString();
					DefaultHttpClient mHttpClient = new DefaultHttpClient();
					HttpPost mPost = new HttpPost(path);
					List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
					pairs.add(new BasicNameValuePair("username", usename));
					pairs.add(new BasicNameValuePair("password", password));
					pairs.add(new BasicNameValuePair("usertype", "2"));
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

						}

					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

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
