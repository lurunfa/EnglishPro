package com.bupt.english.student_cjcx;
/*
 * 分数的主页面
 */
import com.bupt.english.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StudentGradeContent extends Activity{
	TextView show_task,show_grade,show_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentcontent);
		Intent intent = getIntent();
		String task = intent.getStringExtra("task");
		String grade = intent.getStringExtra("grade");
		String content = intent.getStringExtra("content");
		show_task = (TextView) findViewById(R.id.show_studentgrade_task);
		show_grade = (TextView) findViewById(R.id.show_studentgrade_grade);
		show_content= (TextView) findViewById(R.id.show_studentgrade_evaluate);
		show_task.setText("任务名称："+task);
		show_grade.setText("分数："+grade);
		show_content.setText("老师建议："+content);
	}
}
