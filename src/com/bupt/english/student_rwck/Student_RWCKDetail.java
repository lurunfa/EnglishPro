//学生上传文件界面。点击需要上传的文件类型，To：SDFileExplorer选择文件 
package com.bupt.english.student_rwck;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.bupt.english.main.AppClass;
import com.bupt.english.main.R;
import com.bupt.english.main.R.layout;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Student_RWCKDetail extends Activity {
	TextView ppttext, wordtext, videotext, voicetext, taskTitle;
	String videoPath = "";
	String audioPath = "";
	String wordPath = "";
	String pptPath = "";
	String studentId, task,taskId;
	AppClass getpath = new AppClass();
	String path = getpath.getpath()+"english/student/testupload.php";
	AlertDialog.Builder alertDialog, alertDialog2;
	int position;
	ListView listView;
	List<StudentApp> list = new ArrayList<StudentApp>();
	Student_RWCKDetailAdapter adapter;
	ProgressDialog progressDialog;
	RequestParams params = new RequestParams();
	public static final String EXTRA_FILE_CHOOSER = "file_chooser";
	private void toast(CharSequence hint){
	    Toast.makeText(this, hint , Toast.LENGTH_SHORT).show();
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student_rwckdetail);
		
		alertDialog = new AlertDialog.Builder(this).
			    setTitle("提示").
			    setMessage("任务发布成功！").
			    setIcon(R.drawable.logo).
			    setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						setResult(RESULT_OK);
						finish();
					}
				});
		alertDialog2 = new AlertDialog.Builder(this);
		alertDialog2.setTitle("错误");
		alertDialog2.setMessage("发布失败");
		alertDialog2.setIcon(R.drawable.logo);
		alertDialog2.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		ListView listView = (ListView) findViewById(R.id.lv_rwckdetail);
		Intent intent = getIntent();
		studentId = intent.getStringExtra("studentId");
		String needppt = intent.getStringExtra("ppt");
		String needword = intent.getStringExtra("word");
		String needvoice = intent.getStringExtra("voice");
		String needvideo = intent.getStringExtra("video");
		task = intent.getStringExtra("task");
		taskId = intent.getStringExtra("taskId");
		init();

		if (needppt.equals("0")) {
			ppttext.setText("");
		}
		if (needword.equals("0")) {
			wordtext.setText("");
		}
		if (needvoice.equals("0")) {
			voicetext.setText("");
		}
		if (needvideo.equals("0")) {
			videotext.setText("");
		}
		adapter = new Student_RWCKDetailAdapter(Student_RWCKDetail.this,
				R.layout.student_rwckdetail_item, list);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Student_RWCKDetail.this.position = position;
				Intent intent = new Intent(Student_RWCKDetail.this,
						SDFileExplorer.class);
				intent.putExtra("type", list.get(position).gettype());
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				    startActivityForResult(intent , 1);
		    	else
		    		toast(getText(R.string.sdcard_unmonted_hint));
				
				
			}
		});
		Button reset = (Button) findViewById(R.id.reset);
		reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pptPath = "";
				videoPath = "";
				audioPath = "";
				wordPath = "";
				for (int i = 0; i < 4; i++) {
					list.get(i).setAskText("");
				}
				adapter.notifyDataSetChanged();
			}
		});
		Button button = (Button) findViewById(R.id.sureupload);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View V) {
				// TODO Auto-generated method stub
				if (!audioPath.equals("")) {
					params.addBodyParameter("upload_audio", new File(audioPath));
					System.err.println("audio");
				}  if (!wordPath.equals("")) {
					params.addBodyParameter("upload_word", new File(wordPath));
					System.err.println("word");
				} if (!videoPath.equals("")) {
					params.addBodyParameter("upload_video", new File(videoPath));
					System.err.println("video");
				}  if (!pptPath.equals("")) {
					params.addBodyParameter("upload_ppt", new File(pptPath));
				System.err.println("ppt");
					
				}
				params.addBodyParameter("stuid",studentId);
				params.addBodyParameter("chapter",taskId);
				System.out.println(params.toString());
				
				if (voicetext.getText().toString().equals("")
						&& ppttext.getText().toString().equals("")
						&& videotext.getText().toString().equals("")
						&& wordtext.getText().toString().equals("")) {

					progressDialog = new ProgressDialog(Student_RWCKDetail.this);
					progressDialog.setTitle("正在上传");
					progressDialog.setCancelable(false);
					System.out.println(videoPath + wordPath + audioPath
							+ pptPath);
					HttpUtils http = new HttpUtils();
					http.send(HttpMethod.POST,
							path, params,
							new RequestCallBack<String>() {
								public void onStart() {
									progressDialog.show();
								};

								public void onLoading(long total, long current,
										boolean isUploading) {
									if (isUploading) {
										double percent = (double) current
												/ total;
										DecimalFormat format = new DecimalFormat(
												"0.00%");
										String result = format.format(percent);
										progressDialog.setMessage("upload: "
												+ result);
									} else {
										progressDialog.setMessage("reply: "
												+ current + "/" + total);
									}

								};

								@Override
								public void onFailure(HttpException error,
										String msg) {
									// TODO Auto-generated method stub
									progressDialog.setMessage("失败");
									progressDialog.dismiss();
								}

								@Override
								public void onSuccess(
										ResponseInfo<String> responseInfo) {
									// TODO Auto-generated method stub
									progressDialog.setMessage("成功");
									progressDialog.dismiss();
									alertDialog.create();
									alertDialog.show();
								}
							});
				} else {
					Toast.makeText(Student_RWCKDetail.this, "还有任务没有选择",
							Toast.LENGTH_SHORT).show();

				}
			}
		});

	}

	private void init() {
		// TODO Auto-generated method stub
		taskTitle = (TextView) findViewById(R.id.show_tasktitle);
		taskTitle.setText(task);
		ppttext = (TextView) findViewById(R.id.ask_ppt);
		wordtext = (TextView) findViewById(R.id.ask_word);
		voicetext = (TextView) findViewById(R.id.ask_voice);
		videotext = (TextView) findViewById(R.id.ask_video);
		StudentApp app1 = new StudentApp();
		app1.setImage(R.drawable.ppt1);
		app1.setHint("你选择的ppt");
		app1.settype("ppt");
		list.add(app1);
		StudentApp app2 = new StudentApp();
		app2.setImage(R.drawable.word1);
		app2.setHint("你选择的word");
		app2.settype("word");
		list.add(app2);
		StudentApp app3 = new StudentApp();
		app3.setImage(R.drawable.audio1);
		app3.setHint("你选择的音频");
		app3.settype("audio");
		list.add(app3);
		StudentApp app4 = new StudentApp();
		app4.setHint("你选择的视频");
		app4.setImage(R.drawable.video1);
		app4.settype("video");
		list.add(app4);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		case 1:
			switch (position) {
			case 0:
				if (resultCode == RESULT_OK) {
					list.get(0).setAskText(data.getStringExtra("pathName"));
					pptPath = data.getStringExtra("path");
					ppttext.setText("");
					adapter.notifyDataSetChanged();
				}
				if(resultCode == RESULT_CANCELED){
					toast(getText(R.string.open_file_none));
					return ;
				}
				break;
			case 1:
				if (resultCode == RESULT_OK) {
					list.get(1).setAskText(data.getStringExtra("pathName"));
					wordPath = data.getStringExtra("path");
					wordtext.setText("");
					adapter.notifyDataSetChanged();
				}
				if(resultCode == RESULT_CANCELED){
					toast(getText(R.string.open_file_none));
					return ;
				}
				break;
			case 2:
				if (resultCode == RESULT_OK) {
					list.get(2).setAskText(data.getStringExtra("pathName"));
					audioPath = data.getStringExtra("path");
					voicetext.setText("");
					adapter.notifyDataSetChanged();
				}
				if(resultCode == RESULT_CANCELED){
					toast(getText(R.string.open_file_none));
					return ;
				}
				break;
			case 3:
				if (resultCode == RESULT_OK) {
					list.get(3).setAskText(data.getStringExtra("pathName"));
					videoPath = data.getStringExtra("path");
					videotext.setText("");
					adapter.notifyDataSetChanged();
				}
				if(resultCode == RESULT_CANCELED){
					toast(getText(R.string.open_file_none));
					return ;
				}
				break;

			default:
				break;
			}

		default:
			break;
		}
	}

}
