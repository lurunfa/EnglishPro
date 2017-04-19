//主要是跟住教师查询的选项发送不同的请求给服务器，选择相应的选项后返回数据给Teacher_CJCX

package com.bupt.english.techer_cjcx;

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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Teacher_CJCXDetail extends Activity {
	List<TeacherApp> list = new ArrayList<TeacherApp>();
	ListView listView;
	Teacher_CJCXDetailAdapter adapter;
	int position;
	String taskId,book,unit,teacherId;
	String response;
	AppClass getpath = new AppClass();
	public static String formatString(String s) {
		if (s != null) {
			s = s.replaceAll("\ufeff", "");
		}
		return s;
	}
	Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				String response = (String) msg.obj;
				//System.out.println(response);
				try {
					JSONArray jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						TeacherApp app = new TeacherApp();
						app.setBackData(jsonObject.getString("name"));
						app.setBackId(jsonObject.getString("id"));
						list.add(app);
					}
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case 1:
				String response1 = (String) msg.obj;
				//System.out.println(response1);
				try {
					JSONArray jsonArray = new JSONArray(response1);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						TeacherApp app = new TeacherApp();
						app.setBackId(jsonObject.getString("id"));
						app.setBackData(jsonObject.getString("name"));
						list.add(app);
					}
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case 2:
				String response2 = (String) msg.obj;
				//System.out.println(response2);
				try {
					JSONArray jsonArray = new JSONArray(response2);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						TeacherApp app = new TeacherApp();
						app.setBackData(jsonObject.getString("name"));
						app.setBackId(jsonObject.getString("id"));
						list.add(app);
					}
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			case 3:
				String response3 = (String) msg.obj;
				//System.out.println(response3);
				try {
					JSONArray jsonArray = new JSONArray(response3);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						TeacherApp app = new TeacherApp();
						app.setBackData(jsonObject.getString("name"));
						app.setBackId(jsonObject.getString("id"));
						list.add(app);
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_cjcxdetail);
		listView = (ListView) findViewById(R.id.lv_teacher_cjcxdetail);
		adapter = new Teacher_CJCXDetailAdapter(Teacher_CJCXDetail.this,
				R.layout.cjcxdetail_item, list);
		
		Intent intent = getIntent();
		position = intent.getBundleExtra("bundle").getInt("position");
		book = intent.getBundleExtra("bundle").getString("book");
		unit = intent.getBundleExtra("bundle").getString("unit");
		teacherId = intent.getBundleExtra("bundle").getString(
				"teacherId");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					try {
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpPost = new HttpPost(getpath.getpath()+"english/fabu/book.php");
						//HttpPost httpPost = new HttpPost(getpath.getpath()+"book.php");
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
					break;
				case 1:
					try {
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpPost = new HttpPost(getpath.getpath()+"english/fabu/chapter.php");
						//HttpPost httpPost = new HttpPost(getpath.getpath()+"unit.php");
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("bookNum", book));
						httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
						HttpResponse httpResponse = httpClient.execute(httpPost);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String response = formatString(EntityUtils.toString(httpResponse.getEntity(),"GBK"));
							System.out.println(response);
							Message message = new Message();
							message.what = 1;
							message.obj = response;
							handler.sendMessage(message);
							}
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case 2:
					try {
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpPost = new HttpPost(getpath.getpath()+"english/fabu/unit.php");
						//HttpPost httpPost = new HttpPost(getpath.getpath()+"task.php");
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("chapter", unit));
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						HttpResponse httpResponse = httpClient.execute(httpPost);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String response = formatString(EntityUtils.toString(httpResponse.getEntity(),"GBK"));
							System.out.println(response);
							Message message = new Message();
							message.what = 2;
							message.obj = response;
							handler.sendMessage(message);
							}
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case 3:
					try {
						HttpClient httpClient = new DefaultHttpClient();
						HttpPost httpPost = new HttpPost(getpath.getpath()+"english/fabu/class.php");
						//HttpPost httpPost = new HttpPost(getpath.getpath()+"class.php");
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("teacherId", teacherId));
						httpPost.setEntity(new UrlEncodedFormEntity(params));
						HttpResponse httpResponse = httpClient.execute(httpPost);
						if (httpResponse.getStatusLine().getStatusCode() == 200) {
							String response = formatString(EntityUtils.toString(httpResponse.getEntity(),"GBK"));
							System.out.println(response);
							Message message = new Message();
							message.what = 3;
							message.obj = response;
							handler.sendMessage(message);
							}
					} catch (Exception e) {
						// TODO: handle exception
					}
					break;
				case 4:
					TeacherApp app1 = new TeacherApp();
					app1.setBackData("已经完成");
					app1.setBackId("1");
					list.add(app1);
					TeacherApp app2 = new TeacherApp();
					app2.setBackData("未完成");
					app2.setBackId("0");
					list.add(app2);
					adapter.notifyDataSetChanged();
					break;

				default:
					break;
				}
			}
		}).start();
	
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				TeacherApp app = list.get(position);
				intent.putExtra("backId", app.getBackId());
				intent.putExtra("backData", app.getBackData());
				setResult(RESULT_OK, intent);
				finish();
				
			}
		});

	}
}
