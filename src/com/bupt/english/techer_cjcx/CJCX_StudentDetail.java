//呈现的是该学生的上传的任务信息，以及老师对其的评分，老师将评分和建议填写完成后即可上传，若成功则返回到CJCX_Student页面对下一位学生进行评分；

package com.bupt.english.techer_cjcx;

import java.io.File;
import java.text.DecimalFormat;
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
import org.json.JSONObject;

import com.bupt.english.main.AppClass;
import com.bupt.english.main.R;
import com.bupt.english.student_rwck.Student_RWCKDetail;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CJCX_StudentDetail extends Activity {
	ListView listView;
	EditText edit1, edit2, edit3, edit4, evaluate;
	String grade1, grade2, grade3, grade4, ppt, word, audio, video, grade,
			content, studentId, taskId, flag;
	Button sure;
	int position;
	List<TeacherApp> list = new ArrayList<TeacherApp>();
	ProgressDialog progressDialog;
	AppClass getpath = new AppClass();
	String path = getpath.getpath();
	CJCX_StudentDetailAdapter adapter;
	AlertDialog.Builder ADialog, ADsuccess;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cjcx_studentdetail);

		ADialog = new AlertDialog.Builder(this);
		ADialog.setTitle("提示");
		ADialog.setIcon(R.drawable.logo);
		ADialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub

			}
		});

		ADsuccess = new AlertDialog.Builder(this);
		ADsuccess.setTitle("提示");
		ADsuccess.setIcon(R.drawable.logo);
		ADsuccess.setMessage("评分成功！");
		ADsuccess.setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub

						Intent intent = new Intent();
						intent.putExtra("grade1", grade1);
						intent.putExtra("grade2", grade2);
						intent.putExtra("grade3", grade3);
						intent.putExtra("grade4", grade4);
						intent.putExtra("content", content);
						setResult(RESULT_OK, intent);
						finish();
						
					}
				});

		init();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				CJCX_StudentDetail.this.position = position;
				if (list.get(position).getdata() != null) {

					if (list.get(position).getdata().equals("ppt")) {
						progressDialog = new ProgressDialog(
								CJCX_StudentDetail.this);
						progressDialog.setTitle("正在下载");
						progressDialog.setCancelable(true);
						String dirName = "";
						dirName = Environment.getExternalStorageDirectory()
								.getAbsolutePath() + "/MyDownload/";
						File f = new File(dirName);
						if (!f.exists()) {
							f.mkdir();
						}
						String newFilename = ppt.substring(ppt.lastIndexOf("/") + 1);
						newFilename = dirName + newFilename;
						File file = new File(newFilename);
						if (file.exists()) {
							file.delete();
						}
						HttpUtils httpUtils = new HttpUtils();
						HttpHandler<File> handler = httpUtils.download(path
								+ ppt, newFilename, true, true,
								new RequestCallBack<File>() {
									@Override
									public void onStart() {
										// TODO Auto-generated method stub
										progressDialog.show();
									}

									@Override
									public void onLoading(long total,
											long current, boolean isUploading) {
										if (isUploading) {
											double percent = (double) current
													/ total;
											DecimalFormat format = new DecimalFormat(
													"0.00%");
											String result = format
													.format(percent);
											progressDialog
													.setMessage("upload: "
															+ result);
										} else {
											progressDialog.setMessage("reply: "
													+ current + "/" + total);
										}
									}

									@Override
									public void onFailure(HttpException error,
											String msg) {
										progressDialog.setMessage("失败");
										progressDialog.dismiss();
										ADialog.setMessage("下载失败。");
										ADialog.create();
										ADialog.show();
									}

									@Override
									public void onSuccess(
											ResponseInfo<File> responseInfo) {
										progressDialog.setMessage("成功");
										progressDialog.dismiss();

										ADialog.setMessage("下载完成，请到MyDownload文件夹查看。");
										ADialog.create();
										ADialog.show();
									}
								});
					} else if (list.get(position).getdata().equals("word")) {
						progressDialog = new ProgressDialog(
								CJCX_StudentDetail.this);
						progressDialog.setTitle("正在上传");
						progressDialog.setCancelable(true);
						String dirName = "";
						dirName = Environment.getExternalStorageDirectory()
								+ "/MyDownload/";
						File f = new File(dirName);
						if (!f.exists()) {
							f.mkdir();
						}
						String newFilename = word.substring(word
								.lastIndexOf("/") + 1);
						newFilename = dirName + newFilename;
						File file = new File(newFilename);
						if (file.exists()) {
							file.delete();
						}
						HttpUtils httpUtils = new HttpUtils();
						HttpHandler<File> handler = httpUtils.download(path
								+ word, newFilename, true, true,
								new RequestCallBack<File>() {
									@Override
									public void onStart() {
										// TODO Auto-generated method stub
										progressDialog.show();
									}

									@Override
									public void onLoading(long total,
											long current, boolean isUploading) {
										if (isUploading) {
											double percent = (double) current
													/ total;
											DecimalFormat format = new DecimalFormat(
													"0.00%");
											String result = format
													.format(percent);
											progressDialog
													.setMessage("upload: "
															+ result);
										} else {
											progressDialog.setMessage("reply: "
													+ current + "/" + total);
										}
									}

									@Override
									public void onFailure(HttpException error,
											String msg) {
										progressDialog.setMessage("失败");
										progressDialog.dismiss();
										ADialog.setMessage("下载失败。");
										ADialog.create();
										ADialog.show();
									}

									@Override
									public void onSuccess(
											ResponseInfo<File> responseInfo) {
										progressDialog.setMessage("成功");
										progressDialog.dismiss();

										ADialog.setMessage("下载完成，请到MyDownload文件夹查看。");
										ADialog.create();
										ADialog.show();
									}
								});
						;
					} else if (list.get(position).getdata().equals("audio")) {
						Intent intent = new Intent(CJCX_StudentDetail.this,
								VideoPlay.class);
						intent.putExtra("path", path
								+ list.get(position).getdatapath());
						startActivityForResult(intent, 1);
					} else if (list.get(position).getdata().equals("video")) {
						Intent intent = new Intent(CJCX_StudentDetail.this,
								VideoPlay.class);
						intent.putExtra("path", path
								+ list.get(position).getdatapath());
						startActivityForResult(intent, 1);
					} else {
						Toast.makeText(CJCX_StudentDetail.this, "没有文件上传",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(CJCX_StudentDetail.this, "没有文件",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		sure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				content = evaluate.getText().toString();
				grade1 = edit1.getText().toString();
				grade2 = edit2.getText().toString();
				grade3 = edit3.getText().toString();
				grade4 = edit4.getText().toString();
				if (grade1.length()>0&&grade2.length()>0&&grade3.length()>0&&grade4.length()>0&&content.length()>0) {

				
				int f1 = Integer.parseInt(edit1.getText().toString());
				int f2 = Integer.parseInt(edit2.getText().toString());
				int f3 = Integer.parseInt(edit3.getText().toString());
				int f4 = Integer.parseInt(edit4.getText().toString());
				grade = String.valueOf((f1 + f2 + f3 + f4) / 4);
				
				// TODO Auto-generated method stub
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							HttpClient client = new DefaultHttpClient();
							HttpPost httpPost = new HttpPost(path
									+ "english/fabu/evaluate.php");
							/*System.out.println(getpath
									+ "english/fabu/evaluate.php");*/
							List<NameValuePair> pairs = new ArrayList<NameValuePair>();
							pairs.add(new BasicNameValuePair("stuid", studentId));
							pairs.add(new BasicNameValuePair("chapter", taskId));
							pairs.add(new BasicNameValuePair("score", grade));
							pairs.add(new BasicNameValuePair("sug", content));
							//System.out.println(pairs);
							httpPost.setEntity(new UrlEncodedFormEntity(pairs,"utf-8"));
							HttpResponse httpResponse = client
									.execute(httpPost);
							System.out.println(httpResponse.getStatusLine()
									.getStatusCode());
							if (httpResponse.getStatusLine().getStatusCode() == 200) {
								String response = EntityUtils.toString(
										httpResponse.getEntity(), "utf-8");
								Message message = new Message();
								message.what = 0;
								message.obj = response;
								handler.sendMessage(message);

							}

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}).start();}else{
					AlertDialog.Builder diaBuilder = new AlertDialog.Builder(
							CJCX_StudentDetail.this);
					diaBuilder.setTitle("任务评分");
					diaBuilder.setMessage("有一项没有填评分失败");
					diaBuilder.setCancelable(false);
					diaBuilder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialogInterface,
										int which) {
									// TODO Auto-generated method stub
									return;
								}
							});

					diaBuilder.show();
				}
			}

		});

	}

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				String response = (String) msg.obj;
				// response =
				try {
					JSONObject jsonObject = new JSONObject(response);
					flag = jsonObject.getString("flag");
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (flag.equals("success")) {
					ADsuccess.create();
					ADsuccess.show();
				} else {
					AlertDialog.Builder diaBuilder = new AlertDialog.Builder(
							CJCX_StudentDetail.this);
					diaBuilder.setTitle("任务评分");
					diaBuilder.setMessage("评分失败");
					diaBuilder.setCancelable(false);
					diaBuilder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialogInterface,
										int which) {
									// TODO Auto-generated method stub
									return;
								}
							});

					diaBuilder.show();
				}

				break;

			default:
				break;
			}
		}
	};

	private void init() {
		// TODO Auto-generated method stub
		listView = (ListView) findViewById(R.id.lv_cjcxstudentdetail);
		edit1 = (EditText) findViewById(R.id.edit1);
		edit2 = (EditText) findViewById(R.id.edit2);
		edit3 = (EditText) findViewById(R.id.edit3);
		edit4 = (EditText) findViewById(R.id.edit4);
		evaluate = (EditText) findViewById(R.id.editevaluate);

		sure = (Button) findViewById(R.id.evaluate);

		Intent intent = getIntent();
		edit1.setText(intent.getStringExtra("grade1"));
		edit2.setText(intent.getStringExtra("grade2"));
		edit3.setText(intent.getStringExtra("grade3"));
		edit4.setText(intent.getStringExtra("grade4"));
		studentId = intent.getStringExtra("studentId");
		taskId = intent.getStringExtra("taskId");
		sure = (Button) findViewById(R.id.evaluate);
		ppt = intent.getStringExtra("ppt");
		word = intent.getStringExtra("word");
		audio = intent.getStringExtra("audio");
		video = intent.getStringExtra("video");
		TeacherApp apptask = new TeacherApp();
		apptask.setdata(intent.getStringExtra("task"));
		list.add(apptask);
		if (ppt.length() > 0) {
			TeacherApp app1 = new TeacherApp();
			app1.setdata("ppt");
			app1.setdatapath(ppt);
			list.add(app1);
		}
		if (word.length() > 0) {
			TeacherApp app2 = new TeacherApp();
			app2.setdata("word");
			app2.setdatapath(word);
			list.add(app2);
		}
		if (audio.length() > 0) {
			TeacherApp app3 = new TeacherApp();
			app3.setdata("audio");
			app3.setdatapath(audio);
			list.add(app3);
		}
		if (video.length() > 0) {
			TeacherApp app4 = new TeacherApp();
			app4.setdata("video");
			app4.setdatapath(video);
			list.add(app4);
		}
		adapter = new CJCX_StudentDetailAdapter(CJCX_StudentDetail.this,
				R.layout.cjcx_studentdetail_item, list);
		listView.setAdapter(adapter);

	}
}
