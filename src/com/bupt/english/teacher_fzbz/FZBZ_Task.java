package com.bupt.english.teacher_fzbz;
/*
 * 选择任务列表
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
import org.json.JSONException;
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

public class FZBZ_Task extends Activity {
	private List<AppClass> taskList = new ArrayList<AppClass>();
	AppClass getpath = new AppClass();
	String path = getpath.getpath()+"english/fabu/unit.php";

	public String chapter,task;
	public static final int SHOW_RESPONSE = 0;
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
				//Log.d("TaskChose", response);
				String taskTitle = "";
				String taskId = "";
				System.out.println(response);
				//String taskContent = "";
				
				JSONObject jsonObject = null;
				try {
					JSONArray jsonArray = new JSONArray(response);
					System.out.println(jsonArray);
					for (int i = 0; i < jsonArray.length(); i++) {
						jsonObject = jsonArray.getJSONObject(i);
						taskTitle = jsonObject.getString("name");
						taskId = jsonObject.getString("id");
						//Log.d("FZBZ", taskTitle);
					
						AppClass task = new AppClass();
						task.setTaskTitle(taskTitle);
						task.setTaskId(taskId);
						taskList.add(task);
						TaskAdapter adapter = new TaskAdapter(FZBZ_Task.this,
								R.layout.task_item, taskList);
						ListView listView = (ListView) findViewById(R.id.showtasktitle);
						listView.setAdapter(adapter);
						listView.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> arg0, View view,
									int position, long id) {
								AppClass task = taskList.get(position);
								Intent intent = new Intent();
								intent.putExtra("data_return", task.getTaskTitle());
								intent.putExtra("data_back", task.getTaskId());
								setResult(RESULT_OK,intent);
								finish();
								
								
								
							}

						});
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		setContentView(R.layout.fzbz_task);
		Intent intent = getIntent();
		chapter = intent.getStringExtra("chapter");
		//Log.d("task-unit", chapter);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					HttpClient httpClient = new DefaultHttpClient();
					//HttpPost httpPost = new HttpPost("http://10.110.211.142:8011/post2.php");
					HttpPost httpPost = new HttpPost(path);
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("chapter", chapter));
					//System.out.println(params);
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					HttpResponse httpResponse = httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						String response = formatString(EntityUtils.toString(httpResponse
								.getEntity()));
						Message message = new Message();
						message.what = SHOW_RESPONSE;
						message.obj = response;
						handler.sendMessage(message);

					}

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}).start();
		


	}




}
