package com.bupt.english.teacher_fzbz;
/*
 * 这是老师布置任务的主页面
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TeacherFZBZ extends Activity {
	private List<AppClass> appList = new ArrayList<AppClass>();
	private List<AppClass> fzList = new ArrayList<AppClass>();
	private TeacherFZBZAdapter adapter;
	AlertDialog.Builder alertDialog;
	private String bookName = "";
	String bookNum = "";
	String unitName = "";
	String taskId = "";
	String teacherId = "";
	String className = "";
	String deadline = "";
	String beginDate = "";
	String unitId = "";
	String groupBack;
	AppClass getpath = new AppClass();
	String path = getpath.getpath()+"english/fabu/fabu.php";
	//String path = getpath.getpath()+"ceshi.php";
	

	private static final int SHOW_RESPONSE = 0;
	public static String formatString(String s) {
	    if (s != null) {
	          s = s.replaceAll("\ufeff", "");
	    }
	    return s;
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_RESPONSE:
				String response = (String) msg.obj;
				System.err.println(response);
				try {
					JSONObject jsonObject = new JSONObject(response);
					String flag = jsonObject.getString("flag");
					System.out.println(flag);
					if(flag.equals("fail")){
						Toast.makeText(TeacherFZBZ.this, "发布失败", Toast.LENGTH_SHORT).show();
					}
					else{
						alertDialog.create();
						alertDialog.show();
					}
					
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
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_fzbz);
		alertDialog = new AlertDialog.Builder(this).
			    setTitle("提示").
			    setMessage("任务发布成功！").
			    setIcon(R.drawable.logo).
			    setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		
		Intent intent = getIntent();
		teacherId = intent.getStringExtra("teacherId");
		init();
		//选项均以一个listview去呈现
		adapter = new TeacherFZBZAdapter(TeacherFZBZ.this,
				R.layout.teacher_fzbz_item, appList);
		ListView showAll = (ListView) findViewById(R.id.listView_teacher);
		showAll.setAdapter(adapter);
		showAll.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				switch (position) {
				case 0:
					Intent intent = new Intent(TeacherFZBZ.this,
							FZBZ_Book.class);
					startActivityForResult(intent, 0);
					break;
				case 1:
					if (appList.get(0).getBackData() == null) {
						Toast.makeText(TeacherFZBZ.this, "请先选择册",
								Toast.LENGTH_SHORT).show();
					} else {
					
						Intent intent1 = new Intent(TeacherFZBZ.this,
								FZBZ_Unit.class);
						intent1.putExtra("bookNum", bookNum);
						startActivityForResult(intent1, 1);
					}
					break;
				case 2:
					if (appList.get(1).getBackData() == null) {
						Toast.makeText(TeacherFZBZ.this, "请先选择单元",
								Toast.LENGTH_SHORT).show();
					} else {
						Intent intent2 = new Intent(TeacherFZBZ.this,
								FZBZ_Task.class);
						intent2.putExtra("chapter", unitId);
						startActivityForResult(intent2, 2);

					}
					break;
				case 3:
					Intent intent3 = new Intent(TeacherFZBZ.this,
							FZBZ_Class.class);
					intent3.putExtra("teacherId", teacherId);
					startActivityForResult(intent3, 3);
					break;
				case 4:
					if ( appList.get(3).getBackData() != null && appList
							.get(4).getBackData() == null) {
						Intent intent4 = new Intent(TeacherFZBZ.this,
								Classify.class);
						intent4.putExtra("className", className);
						intent4.putExtra("before", "1");
						startActivityForResult(intent4, 4);

					} else if (appList.get(3).getBackData() != null && appList
							.get(4).getBackData() != null) {
						Intent intent6 = new Intent(TeacherFZBZ.this,
								Classify.class);
						intent6.putExtra("before", groupBack);
						intent6.putExtra("className", className);
						startActivityForResult(intent6, 4);
					} else {
						Toast.makeText(TeacherFZBZ.this, "请先选择班级",
								Toast.LENGTH_SHORT).show();
					}
					break;
				case 5:
				if (appList.get(5).getBackData() !=null) {
					Intent intent5 = new Intent(TeacherFZBZ.this,
							FZBZ_Date.class);
					intent5.putExtra("beginDate", beginDate);
					intent5.putExtra("deadline", deadline);
					startActivityForResult(intent5, 5);
			
				}else{
						Intent intent5 = new Intent(TeacherFZBZ.this,
								FZBZ_Date.class);
						intent5.putExtra("beginDate", "2015-02-01");
						intent5.putExtra("deadline", "2015-02-01");
						startActivityForResult(intent5, 5);
				}
					break;

				default:
					break;
				}
			}
		});
		//这是对任务数据的提交
		Button sureFB = (Button) findViewById(R.id.sure_fabu);
		sureFB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
					if (appList.get(0).getBackData()==null||appList.get(1).getBackData()==null||appList.get(2).getBackData()==null||appList.get(3).getBackData()==null||appList.get(4).getBackData()==null||appList.get(5).getBackData()==null) {
						Toast.makeText(TeacherFZBZ.this, "你有其中一项没填完",
								Toast.LENGTH_SHORT).show();
					} else {
						new Thread(new Runnable() {

							@Override
							public void run() {
								try {
									HttpClient httpClient = new DefaultHttpClient();
									HttpPost httpPost = new HttpPost(path);
									List<NameValuePair> params = new ArrayList<NameValuePair>();
									params.add(new BasicNameValuePair(
											"taskId",taskId));
									params.add(new BasicNameValuePair(
											"teacherId", teacherId));
									params.add(new BasicNameValuePair(
											"className", className));
									params.add(new BasicNameValuePair(
											"beginDate", beginDate));
									params.add(new BasicNameValuePair(
											"deadline", deadline));
									params.add(new BasicNameValuePair("group",
											formatString(groupBack)));
									;
									System.out.println(params);
									httpPost.setEntity(new UrlEncodedFormEntity(
											params, "utf-8"));
									HttpResponse httpResponse = httpClient
											.execute(httpPost);
									if (httpResponse.getStatusLine()
											.getStatusCode() == 200) {
										String response = formatString(EntityUtils.toString(
												httpResponse.getEntity(), "utf-8"));
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
			
		});

	}
//这是对每次选项数据的返回以及保存
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 0:
			if (resultCode == RESULT_OK) {
				bookNum = data.getStringExtra("bookNum");
				bookName = data.getStringExtra("bookName");
				appList.get(0).setBackData(bookName);
				adapter.notifyDataSetChanged();
			}

			break;
		case 1:
			if (resultCode == RESULT_OK) {
				String unitName = data.getStringExtra("unitName");
				unitId = data.getStringExtra("unitId");
				appList.get(1).setBackData(unitName);
				adapter.notifyDataSetChanged();
			}
			break;
		case 2:
			if (resultCode == RESULT_OK) {
				String taksName = data.getStringExtra("data_return").substring(
						0, 5);
				taskId = data.getStringExtra("data_back");
				appList.get(2).setBackData(taksName);
				adapter.notifyDataSetChanged();
			}
			break;
		case 3:
			if (resultCode == RESULT_OK) {
				String classNum = data.getStringExtra("classNum");
				 className = data.getStringExtra("className");
				appList.get(3).setBackData(className);
				adapter.notifyDataSetChanged();
			}
			break;
		case 4:
			if (resultCode == RESULT_OK) {
				groupBack = data.getStringExtra("groupback");
				appList.get(4).setBackData("已分组");
				adapter.notifyDataSetChanged();
			}
			break;
		case 5:
			if (resultCode == RESULT_OK) {
				deadline = data.getStringExtra("deadline");
				beginDate = data.getStringExtra("beginDate");
				appList.get(5).setBackData(deadline);
				adapter.notifyDataSetChanged();
			}

		default:
			break;
		}
	}
//初始化选项
	private void init() {
		AppClass appBook = new AppClass();
		appBook.setimageId(R.drawable.chose_book);
		appBook.setHint("请选择书名");
		appList.add(appBook);

		AppClass appChapter = new AppClass();
		appChapter.setimageId(R.drawable.chose_unit);
		appChapter.setHint("请选择单元");
		appList.add(appChapter);

		AppClass appTask = new AppClass();
		appTask.setimageId(R.drawable.chose_task);
		appTask.setHint("请选择实验");
		appList.add(appTask);

		AppClass appClass = new AppClass();
		appClass.setimageId(R.drawable.chose_class);
		appClass.setHint("请选择班级");
		appList.add(appClass);

		AppClass appClassify = new AppClass();
		appClassify.setimageId(R.drawable.chose_group);
		appClassify.setHint("请选择分组");
		appList.add(appClassify);

		AppClass appDate = new AppClass();
		appDate.setimageId(R.drawable.chose_date);
		appDate.setHint("请选择日期");
		appList.add(appDate);
	}

}
