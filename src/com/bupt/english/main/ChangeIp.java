package com.bupt.english.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class ChangeIp extends Activity{
	EditText editText;
	Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.changeip);
		editText = (EditText) findViewById(R.id.edit_ip);
		editText.setText(AppClass.ip);
		button = (Button)findViewById(R.id.btn_sureIp);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ip = editText.getText().toString();
				AppClass.ip = ip;
				AppClass appClass = new AppClass();
				appClass.setPath("http://"+ip+"/");
				System.out.println(appClass.getpath());
				finish();
			}
		});
	}

}
