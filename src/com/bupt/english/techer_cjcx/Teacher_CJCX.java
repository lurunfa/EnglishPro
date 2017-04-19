//教师评分的主界面，第一步是选择查询要评分的实验和班级进入Teacher_CJCXDetail分别选择册班级单元实验完成情况，进入查询后的结果即进去CJCX_Student.

package com.bupt.english.techer_cjcx;

import java.util.ArrayList;
import java.util.List;

import com.bupt.english.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Teacher_CJCX extends Activity {
	List<TeacherApp> list = new ArrayList<TeacherApp>();
	Teacher_CJCXAdapter adapter;
	ListView listView;
	int position;
	String book = "";
	String unit = "";
	String task = "";
	String classNum = "";
	String teacherId, sign,taskName,flag,classname;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_cjcx);
		Intent intent = getIntent();
		teacherId = intent.getStringExtra("teacherId");
		init();
		adapter = new Teacher_CJCXAdapter(Teacher_CJCX.this,
				R.layout.teacher_cjcx_item, list);
		listView = (ListView) findViewById(R.id.lv_teacher_cjcx);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Teacher_CJCX.this.position = position;
				Intent intent = new Intent(Teacher_CJCX.this,
						Teacher_CJCXDetail.class);
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				bundle.putString("book", book);
				bundle.putString("unit", unit);
				bundle.putString("teacherId", teacherId);
				intent.putExtra("bundle", bundle);
				startActivityForResult(intent, 1);
			}
		});
		Button button = (Button) findViewById(R.id.select);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			if (list.get(0).gettext() != null
						&& list.get(1).gettext() != null
						&& list.get(2).gettext() != null
						&& list.get(3).gettext() != null
						&& list.get(4).gettext() != null) {
					Intent intent = new Intent(Teacher_CJCX.this,CJCX_Student.class);
					intent.putExtra("taskId", task);
					intent.putExtra("taskName", taskName);
					intent.putExtra("sign", sign);
					intent.putExtra("teacherId", teacherId);
					intent.putExtra("classNum", classname);
					startActivity(intent);

				}else {
					Toast.makeText(Teacher_CJCX.this, "你还有项目没有选择", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if (resultCode == RESULT_OK) {
				switch (position) {
				case 0:
					book = data.getStringExtra("backId");
					list.get(0).settext(data.getStringExtra("backData"));
					System.err.println(book);
					adapter.notifyDataSetChanged();
					break;
				case 1:
					unit = data.getStringExtra("backId");
					list.get(1).settext(data.getStringExtra("backData"));
					adapter.notifyDataSetChanged();
					break;
				case 2:
					task = data.getStringExtra("backId");
					taskName = data.getStringExtra("backData");
					list.get(2).settext(data.getStringExtra("backData"));
					adapter.notifyDataSetChanged();
					break;
				case 3:
					classNum = data.getStringExtra("backId");
					classname = data.getStringExtra("backData");
					list.get(3).settext(data.getStringExtra("backData"));
					adapter.notifyDataSetChanged();
					break;
				case 4:
					sign = data.getStringExtra("backId");
					list.get(4).settext(data.getStringExtra("backData"));
					adapter.notifyDataSetChanged();
					break;
				case 5:
					flag = data.getStringExtra("backId");
					list.get(5).settext(data.getStringExtra("backData"));
					adapter.notifyDataSetChanged();
					break;
				default:
					break;
				}
			}
			break;

		default:
			break;
		}
	}

	private void init() {
		// TODO Auto-generated method stub
		TeacherApp app1 = new TeacherApp();
		app1.setimageId_CJCX(R.drawable.chose_book);
		app1.setHint("选择的课本是");
		list.add(app1);
		TeacherApp app2 = new TeacherApp();
		app2.setimageId_CJCX(R.drawable.chose_unit);
		app2.setHint("选择的单元是");
		list.add(app2);
		TeacherApp app3 = new TeacherApp();
		app3.setimageId_CJCX(R.drawable.chose_task);
		app3.setHint("选择的实验是");
		list.add(app3);
		TeacherApp app4 = new TeacherApp();
		app4.setimageId_CJCX(R.drawable.chose_class);
		app4.setHint("选择的班级是");
		list.add(app4);
		TeacherApp app5 = new TeacherApp();
		app5.setimageId_CJCX(R.drawable.chose_finish);
		app5.setHint("完成情况");
		list.add(app5);
	}

}
