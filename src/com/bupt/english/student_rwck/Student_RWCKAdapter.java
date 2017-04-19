package com.bupt.english.student_rwck;

import java.util.List;

import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Student_RWCKAdapter extends ArrayAdapter<StudentApp> {
	private int resourceId;

	public Student_RWCKAdapter(Context context, int resource,
			List<StudentApp> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		StudentApp app = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView show_task = (TextView) view.findViewById(R.id.show_taskName);
		TextView show_date = (TextView) view.findViewById(R.id.show_date);
		TextView show_partner = (TextView)view.findViewById(R.id.show_partner);
		TextView show_flag = (TextView)view.findViewById(R.id.show_flag);
		show_task.setText("任务名称 ："+app.getTaskName());
		show_date.setText("结束时间 ："+app.getDeadline());
		show_partner.setText("同组："+app.getPartnet());
		
		if (app.getFlag().equals("unfinished")) {
			show_flag.setText("未完成");
		}else if(app.getFlag().equals("finished")){
			show_flag.setText("已完成");
		}else if (app.getFlag().equals("2")) {
			show_flag.setText("已上传");
		}else {
			show_flag.setText("未完成");
		}
		
		return view;
		
	}

}
