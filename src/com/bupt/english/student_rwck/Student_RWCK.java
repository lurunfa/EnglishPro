//学生去做任务。呈现学生任务列表。点击item， To：Student_RWCKDetail上传文件。

package com.bupt.english.student_rwck;

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

public class Student_RWCK extends Activity{
	
	AppClass getpath = new AppClass();
	//String path = getpath.getpath()+"post4.php";
	String path = getpath.getpath()+"english/student/view.php";
	String studentId = "";
	int position;
	ListView listView;
	List<StudentApp> list = new ArrayList<StudentApp>();
	Student_RWCKAdapter adapter;
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				String response = (String) msg.obj;
				System.out.println(response);
				try {
					
					JSONArray jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++) {
						StudentApp app = new StudentApp();
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						app.settaskId(jsonObject.getString("chapter"));
						//app.setBeginDate(jsonObject.getString("beginDate"));
						app.setDeadline(jsonObject.getString("deadline"));
						app.settaskName(jsonObject.getString("cname"));
						app.setPartner(jsonObject.getString("sname"));
						app.setword(jsonObject.getString("word"));
						app.setppt(jsonObject.getString("ppt"));
						app.setvoice(jsonObject.getString("audio"));
						app.setvideo(jsonObject.getString("video"));
						app.setFlag(jsonObject.getString("sign"));
						app.settaskId(jsonObject.getString("chapter"));
						list.add(app);
					}
					
				
					
					adapter = new Student_RWCKAdapter(Student_RWCK.this, R.layout.student_rwck_item, list);
					adapter.notifyDataSetChanged();
					listView.setAdapter(adapter);
					
					listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub
							StudentApp app1 = list.get(position);
							Student_RWCK.this.position = position;
							if (list.get(position).getFlag().equals("finished")) {
								return;
							}else{
							Intent intent = new Intent(Student_RWCK.this,Student_RWCKDetail.class);
							intent.putExtra("task", app1.getTaskName());
							intent.putExtra("studentId", studentId);
							intent.putExtra("taskId", app1.getTaskId());
							intent.putExtra("ppt", app1.getppt());
							intent.putExtra("word",app1.getword());
							intent.putExtra("voice", app1.getvoice());
							intent.putExtra("video", app1.getvideo());
							startActivityForResult(intent, 1);
						}
						}
					});
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_rwck);
		Intent intent = getIntent();
		studentId = intent.getStringExtra("studentId");
		listView = (ListView) findViewById(R.id.lv_studentrwck);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(path);
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					//System.out.println(studentId);
					params.add(new BasicNameValuePair("stuid", studentId));
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					HttpResponse httpResponse = httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						String response = formatString(EntityUtils.toString(httpResponse.getEntity(),"GBK"));
						Message message = new Message();
						message.what = 0;
						message.obj = response;
						handler.sendMessage(message);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				list.get(position).setFlag("2");
				adapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
	}
	public static String formatString(String s) {
	    if (s != null) {
	          s = s.replaceAll("\ufeff", "");
	    }
	    return s;
	}
}
