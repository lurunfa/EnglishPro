package com.bupt.english.teacher_fzbz;
/*
 * 选择日期
 */
import com.bupt.english.main.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class FZBZ_Date extends Activity {

	private Button dateBegin, dateEnd;
	private String strBegin,strEnd,str1,str2;

	@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.date);
	Intent intent = getIntent();
	//这个也是让老师能看到之前自己设定得日期
	 str1 = intent.getStringExtra("beginDate");
	 str2 = intent.getStringExtra("deadline");
	/*if (str1.equals("0000-00-00")) {
		str1 = "2015-01"
	}*/

	dateBegin = (Button) findViewById(R.id.date_begin);
	if (str1.equals("2015-02-01")) {
		dateBegin.setText("0000-00-00");
	}else {
		dateBegin.setText(str1);
	}
	dateBegin.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			DatePickerDialog DBegin = new DatePickerDialog(FZBZ_Date.this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					strBegin = String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth);
					dateBegin.setText(strBegin);
					
					
				}
			}, Integer.valueOf(str1.substring(0, 4)).intValue(), Integer.valueOf(str1.substring(5, 6)).intValue(),Integer.valueOf(str1.substring(7)).intValue());
			DBegin.show();
		}
	});
	
	dateEnd = (Button) findViewById(R.id.date_end);
	if (str1.equals("2015-02-01")) {
		dateEnd.setText("0000-00-00");
	}else {
		dateBegin.setText(str2);
	}
	dateEnd.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			DatePickerDialog DEnd = new DatePickerDialog(FZBZ_Date.this, new DatePickerDialog.OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					strEnd = String.format("%d-%d-%d", year, monthOfYear+1, dayOfMonth);
					dateEnd.setText(strEnd);
					
				}
			}, Integer.valueOf(str2.substring(0, 4)).intValue(), Integer.valueOf(str2.substring(5, 6)).intValue(),Integer.valueOf(str2.substring(7)).intValue());
			DEnd.show();
		}
	});
	
	Button sureDate = (Button) findViewById(R.id.suredate);
	sureDate.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent5 = new Intent();
			intent5.putExtra("beginDate", strBegin);
			intent5.putExtra("deadline", strEnd);
			setResult(RESULT_OK, intent5);
			finish();
			
		}
	});

}


}
