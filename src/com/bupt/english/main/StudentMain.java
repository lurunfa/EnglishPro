//学生主界面，To：Student_RWCK去做任务 & StudentGrade查看成绩
package com.bupt.english.main;

import java.io.File;

import com.bupt.english.student_cjcx.StudentGrade;
import com.bupt.english.student_rwck.SDFileExplorer;
import com.bupt.english.student_rwck.Student_RWCK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StudentMain extends Activity {
	String studentId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentmain);
		Intent intent = getIntent();
		studentId = intent.getStringExtra("ID");//接收传递的ID，以后可以写成application类或者以静态类存储
		Button student_rwck = (Button) findViewById(R.id.student_rwuck);
		//跳转到学生任务查看的界面中
		student_rwck.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//这个判断SD卡暂时无效
				if (Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					Intent intent = new Intent(StudentMain.this,
							Student_RWCK.class);
					intent.putExtra("studentId", studentId);
					startActivity(intent);
				} else {
					Toast.makeText(StudentMain.this, "请插入sdcard",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
		//跳转到学生成绩查询的页面中
		Button student_cjcx = (Button) findViewById(R.id.student_cjcx);
		student_cjcx.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
					Intent intent = new Intent(StudentMain.this,
							StudentGrade.class);
					intent.putExtra("studentId", studentId);
					startActivity(intent);
			

			}
		});
	}

}
