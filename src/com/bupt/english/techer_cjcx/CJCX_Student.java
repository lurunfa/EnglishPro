//从查询条件的页面Teacher_CJCX向服务器传输查询条件，服务器返回老师没有评价的所有学生，呈现的是一个学生的列表。
//选择其中的一位学生后进入CJCX_Student查看该位学生的任务的详细内容即进入CJCX_StudentDetail界面；

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CJCX_Student extends Activity {
	String taskName, taskId, classNum, teacherId, grade1, grade2, grade3,
			grade4;
	String sign = "";
	List<TeacherApp> list = new ArrayList<TeacherApp>();
	CJCX_StudentAdapter adapter;
	int position;
AppClass getpath = new AppClass();
	public static String formatString(String s) {
		if (s != null) {
			s = s.replaceAll("\ufeff", "");
		}
		return s;
	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				String response = (String) msg.obj;
				//System.out.println(response);
				try {
					JSONArray jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						TeacherApp app = new TeacherApp();
						app.setstuid(jsonObject.getString("stuid"));
						app.setaudiopath(jsonObject.getString("audio_path"));
						app.setvideopath(jsonObject.getString("video_path"));
						app.setName(jsonObject.getString("sname"));
						app.setwordpath(jsonObject.getString("word_path"));
						app.setpptpath(jsonObject.getString("ppt_path"));
						list.add(app);
					}
					ListView listView = (ListView) findViewById(R.id.lv_cjcx_student);
					adapter = new CJCX_StudentAdapter(CJCX_Student.this,
							R.layout.cjcx_student_item, list);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							/*if (sign.equals("未完成")) {
								Toast.makeText(CJCX_Student.this, "没有该学生的上传任务",
										Toast.LENGTH_SHORT).show();
							} else {*/
								CJCX_Student.this.position = position;
								Intent intent = new Intent(CJCX_Student.this,CJCX_StudentDetail.class);
								System.out.println(list.get(position).getvideopath());
								intent.putExtra("name", list.get(position)
										.getname());
								intent.putExtra("ppt", list.get(position)
										.getpptpath());
								intent.putExtra("word", list.get(position)
										.getwordpath());
								intent.putExtra("audio", list.get(position)
										.getaudiopath());
								intent.putExtra("video", list.get(position)
										.getvideopath());
								intent.putExtra("studentId", list.get(position).getstuid());
								intent.putExtra("grade1", grade1);
								intent.putExtra("grade2", grade2);
								intent.putExtra("grade3", grade3);
								intent.putExtra("grade4", grade4);
								intent.putExtra("taskId", taskId);
								startActivityForResult(intent, 1);
							}
						//}
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
		setContentView(R.layout.cjcx_student);
		Intent intent = getIntent();
		taskName = intent.getStringExtra("taskName");
		taskId = intent.getStringExtra("taskId");
		sign = intent.getStringExtra("sign");
		teacherId = intent.getStringExtra("teacherId");
		classNum = intent.getStringExtra("classNum");
		//System.out.println(taskName+sign+taskId+teacherId+classNum);
		TextView show_title = (TextView) findViewById(R.id.show_cjcxtaskTitle);
		show_title.setText("任务名称："+taskName);
		TextView showClass = (TextView) findViewById(R.id.show_cjcxclass);
		showClass.setText("班级号："+classNum);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(getpath.getpath()+"english/fabu/view.php");
					//HttpPost httpPost = new HttpPost(getpath.getpath()+"student.php");
					//HttpPost httpPost = new HttpPost("http://192.168.3.215:8011/"+"student.php");
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("taskId", taskId));
					params.add(new BasicNameValuePair("sign", sign));
					params.add(new BasicNameValuePair("classNum", classNum));
					
					System.out.println(params);
					
					httpPost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
					HttpResponse httpResponse = httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						String response = formatString(EntityUtils.toString(
								httpResponse.getEntity(), "GBK"));
						System.out.println(response);
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
		if (requestCode == 1) {
			switch (requestCode) {
			case 1:
				if (resultCode == RESULT_OK) {
					list.get(position).setflag("2");
					grade1 = data.getStringExtra("grade1");
					grade2 = data.getStringExtra("grade2");
					grade3 = data.getStringExtra("grade3");
					grade4 = data.getStringExtra("grade4");
					adapter.notifyDataSetChanged();

				}
				break;

			default:
				break;
			}
		}
	}
}
