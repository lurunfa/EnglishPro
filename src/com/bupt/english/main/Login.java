package com.bupt.english.main;
/*
 * 用户角色登录主页面
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class Login extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		Button ipButton = (Button)findViewById(R.id.btn_changIp);
		ipButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this,ChangeIp.class);
				startActivity(intent);
			}
		});
		Button teacher_in = (Button) findViewById(R.id.btn1_teacher_in);
		//教师登录按钮
		teacher_in.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this, TeacherLogin.class);//跳转到教师登录
				startActivity(intent);
			}
		});
		//学生登录
		Button student_in = (Button) findViewById(R.id.btn2_student_in);
		student_in.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this,StudentLogin.class);//跳转到学生登录
				startActivity(intent);
			}
		});

	}

}
