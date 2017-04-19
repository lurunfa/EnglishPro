package com.bupt.english.teacher_fzbz;
/*
 * 选择班级
 */
import java.util.ArrayList;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bupt.english.main.AppClass;
import com.bupt.english.main.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FZBZ_Class extends Activity {
	private List<AppClass> ClassList = new ArrayList<AppClass>();
	public String teacherID;
	public static final int SHOW_RESPONSE = 0;
	AppClass getpath = new AppClass();
	String path = getpath.getpath()+"english/fabu/class.php";
	public static String formatString(String s) {
	    if (s != null) {
	          s = s.replaceAll("\ufeff", "");
	    }
	    return s;
	}
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				//Log.d("ClassChose", response);
				try {
					JSONArray jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						String classNum = jsonObject.getString("id");
						String className = jsonObject.getString("name");
						//Log.d("FZBZ", classNum);
						AppClass grade = new AppClass();
						grade.setClassNum(classNum);
						grade.setClassName(className);
						ClassList.add(grade);
						ClassAdapter classAdapter = new ClassAdapter(FZBZ_Class.this, R.layout.class_item, ClassList);
						ListView listView = (ListView) findViewById(R.id.lv_class);
						listView.setAdapter(classAdapter);
						listView.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
								AppClass grade = ClassList.get(position);
								Intent intent = new Intent();
								intent.putExtra("classNum", grade.getClassNum());
								intent.putExtra("className", grade.getClassName());
								setResult(RESULT_OK,intent);
								finish();
								
							}
						});
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			default:
				break;
			}
		};
	};
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fzbz_class);
		Intent intent = getIntent();
		teacherID = intent.getStringExtra("teacherId");
		Log.d("teacherID", teacherID);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(path);
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("teacherId", teacherID));
					//System.out.println(params);
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					HttpResponse httpResponse = httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						String response = formatString(EntityUtils.toString(httpResponse
								.getEntity(),"GBK"));
						Message message = new Message();
						message.what = SHOW_RESPONSE;
						message.obj = response;
						handler.sendMessage(message);
						//Log.d("tag",response);
					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
		


	}


}
