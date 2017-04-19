package com.bupt.english.teacher_fzbz;
/*
 * 分组的主页面
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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;



public class Classify extends Activity {
	EditText editPerGroup;
	Button fenzu;
	AppClass getpath = new AppClass();
	String path = getpath.getpath()+"english/fabu/fenzu.php";
	//String path = "http://192.168.3.244/english/fabu/fenzu.php";
	//String path = "http://192.168.3.218:8011/test4.3.php";
	List<AppClass> list = new ArrayList<AppClass>();
	GroupAdapter adapter ;
	String groupback,className;
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
				System.out.println(response);
				try {
					JSONArray jsonArray = new JSONArray(response);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						AppClass appclass = new AppClass();
						appclass.setgroup(jsonObject.getString("group"));
						appclass.setname(jsonObject.getString("name"));
						appclass.setid(jsonObject.getString("id"));
						list.add(appclass);
						
					}
					adapter.notifyDataSetChanged();
					ListView listView = (ListView) findViewById(R.id.lv_fenzu);
					listView.setAdapter(adapter);
					//这是对其中的某位同学进行组号调整
					listView.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view,
								int position, long id) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(Classify.this,ClasssifyClass.class);
							Bundle bundle = new Bundle();
							bundle.putString("group", list.get(position).getgroup());
							bundle.putString("name", list.get(position).getname());
							bundle.putInt("position", position);
							intent.putExtras(bundle);
							startActivityForResult(intent, 1);
							
						}
					});
					
					
					
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
		setContentView(R.layout.fzbz_classify);
		adapter = new GroupAdapter(Classify.this, R.layout.fenzu_item, list);
		Intent intent4 =getIntent();
		String response = intent4.getStringExtra("before");//这是一个优化，可以使老师确定的分组看回自己的分组
		className = intent4.getStringExtra("className");
		try {
			if (!response.equals("1")) {
				JSONArray jsonArray = new JSONArray(response);
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					AppClass appclass = new AppClass();
					appclass.setgroup(jsonObject.getString("group"));
					appclass.setname(jsonObject.getString("name"));
					list.add(appclass);
				}
				adapter.notifyDataSetChanged();
				ListView listView = (ListView) findViewById(R.id.lv_fenzu);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(Classify.this,ClasssifyClass.class);
						Bundle bundle = new Bundle();
						bundle.putString("group", list.get(position).getgroup());
						bundle.putString("name", list.get(position).getname());
						bundle.putInt("position", position);
						intent.putExtras(bundle);
						startActivityForResult(intent, 1);
						
					}
				});
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		editPerGroup = (EditText) findViewById(R.id.edit_per_group);
		fenzu = (Button) findViewById(R.id.btn);
		fenzu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				list.clear();
				adapter.notifyDataSetChanged();
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							
							String perGroup = editPerGroup.getText().toString();
							HttpClient httpClient = new DefaultHttpClient();
							HttpPost httpPost = new HttpPost(path);
							List<NameValuePair> params = new ArrayList<NameValuePair>();
							params.add(new BasicNameValuePair("className", className));
							params.add(new BasicNameValuePair("per_group",perGroup));
							System.out.println(params);
							httpPost.setEntity(new UrlEncodedFormEntity(params));
							HttpResponse httpResponse = httpClient.execute(httpPost);
							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								String response = formatString(EntityUtils.toString(httpResponse.getEntity(),"utf-8"));
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
		});
		Button button = (Button) findViewById(R.id.surefenzu);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					JSONArray jsonArray = new JSONArray();
					for (int i = 0; i < 1; i++) {
						for (int j = 0; j < list.size(); j++) {
							JSONObject jsonObject = new JSONObject();
							jsonObject.put("name", list.get(j).getname());
							jsonObject.put("group", list.get(j).getgroup());
							jsonObject.put("stuid", list.get(j).getid());
							jsonArray.put(jsonObject);
						}
					}
					groupback = jsonArray.toString();
				} catch (Exception e) {
					// TODO: handle exception
				}
				Intent intent = new Intent();
				intent.putExtra("groupback", groupback);
				setResult(RESULT_OK,intent);
				finish();
			}
		});
		
		
	}
	//这是对某位同学改变分组后的修改原列表的数据
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				Bundle bundle = data.getBundleExtra("bundle");
				list.get(bundle.getInt("position")).setgroup(bundle.getString("group"));
				adapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
	}
}
