package com.bupt.english.teacher_fzbz;
/*
 * 更改某位组员的组号
 */
import com.bupt.english.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClasssifyClass extends Activity {
	private String name,group;
	private EditText editGroup;
	private int position;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classifclass);
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		name = bundle.getString("name");
		group = bundle.getString("group");
		//Log.d("Group", group);
		position = bundle.getInt("position");
		TextView show_befName = (TextView) findViewById(R.id.show_beforeName);
		show_befName.setText(name);
		TextView show_befGroup = (TextView) findViewById(R.id.show_beforeGroup);
		show_befGroup.setText(group);
		editGroup = (EditText) findViewById(R.id.edit_group);
		Button sureChange = (Button) findViewById(R.id.sure_changeGroup);
		sureChange.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String groupChange = editGroup.getText().toString();
				
				Intent intent = new Intent(ClasssifyClass.this,Classify.class);
				Bundle bundle = new Bundle();
				bundle.putInt("position", position);
				bundle.putString("group", groupChange);
				intent.putExtra("bundle", bundle);
				setResult(RESULT_OK, intent);
				finish();
				
			}
		});
		
		
		
	}
}
