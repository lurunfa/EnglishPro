package com.bupt.english.student_cjcx;
/*
 * 学生成绩查询主页面
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class StudentGrade extends Activity{
	List<Student> list = new ArrayList<Student>();
	ListView listView;
	StudentAdapter adapter ;
	String studentId;
	AppClass getpath = new AppClass();
	String path = getpath.getpath();
	//消除utf-8报头（若发现JSON数据表面上看上去是正确却认证不成功则很大可能是前面有一个隐藏的报头）
	public static String formatString(String s) {
	    if (s != null) {
	          s = s.replaceAll("\ufeff", "");
	    }
	    return s;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentgrade);
		listView = (ListView) findViewById(R.id.lv_studentgrade);
		adapter = new StudentAdapter(StudentGrade.this, R.layout.studentgrade_item, list);
		Intent intent = getIntent();
		studentId = intent.getStringExtra("studentId");
		init();//发送网络请求，以后可以对httpclient进行封装不用再写如此多代码。com.exam.http里面是我写的一个封装可以进行使用。
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StudentGrade.this,StudentGradeContent.class);
				intent.putExtra("content", list.get(position).getevaluate());
				intent.putExtra("grade", list.get(position).getgrade());
				intent.putExtra("task", list.get(position).gettask());
				startActivityForResult(intent, 1);
			}
		});
	}

	private void init() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					HttpClient client = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(path+"english/student/getinfo.php");
					List<NameValuePair> pairs = new ArrayList<NameValuePair>();
					pairs.add(new BasicNameValuePair("stuid", studentId));
					httpPost.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse httpResponse = client.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						String response = formatString(EntityUtils.toString(httpResponse.getEntity(),"GBK"));
						Message message = new Message();
						message.what = 0;
						message.obj = response;
						handler.sendMessage(message);
					}else {
						Toast.makeText(StudentGrade.this, "网络错误", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}
	Handler handler= new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				String response = (String) msg.obj;
				System.out.println(response);
				try {
					//对数据进行解释
					JSONArray jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						Student student = new Student();
						student.settask(jsonObject.getString("cname"));
						student.setgrade(jsonObject.getString("score"));
						student.setevaluate(jsonObject.getString("suggestion"));
						list.add(student);
					}
					adapter.notifyDataSetChanged();
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				break;

			default:
				break;
			}
		};
	};
}
