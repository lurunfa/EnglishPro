package com.bupt.english.main;
/*
 * 教师功能选择的主界面
 */

import com.bupt.english.teacher_fzbz.TeacherFZBZ;
import com.bupt.english.techer_cjcx.Teacher_CJCX;

import com.bupt.english.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class TeacherMain extends Activity implements OnClickListener{

private String teacherId;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.teachermain);
	Intent intent = getIntent();
	teacherId = intent.getStringExtra("ID");/*
	Button experimentCreate = (Button)findViewById(R.id.teacher1_sycj);
	experimentCreate.setOnClickListener(this);*/
	Button groupArrangment = (Button)findViewById(R.id.teacher2_fzbz);
	groupArrangment.setOnClickListener(this);/*
	Button teacherRatings = (Button)findViewById(R.id.teacher3_jspf);
	teacherRatings.setOnClickListener(this);*/
	Button queryScore = (Button)findViewById(R.id.teacher4_cjcx);
	queryScore.setOnClickListener(this);
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		/*case R.id.teacher1_sycj:
			Intent intent1 = new Intent();
			startActivity(intent1);
			break;*/
		case R.id.teacher2_fzbz:
			Intent intent2 = new Intent(TeacherMain.this, TeacherFZBZ.class);
			intent2.putExtra("teacherId", teacherId);
			startActivity(intent2);
			break;
		/*case R.id.teacher3_jspf:
			Intent intent3 = new Intent();
			startActivity(intent3);
			break;*/
		case R.id.teacher4_cjcx:
			Intent intent4 = new Intent(TeacherMain.this,Teacher_CJCX.class);
			intent4.putExtra("teacherId", teacherId);
			startActivity(intent4);
			break;

		default:
			break;
		}
	}

}
