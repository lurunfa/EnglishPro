package com.bupt.english.teacher_fzbz;

import java.util.List;


import com.bupt.english.main.AppClass;
import com.bupt.english.main.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<AppClass> {
private int resourceId;
	

	public TaskAdapter(Context context, int textViewResourceId,
			List<AppClass> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppClass task = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView taskTitle = (TextView) view.findViewById(R.id.tasktitle);
		taskTitle.setText(task.getTaskTitle());
		return view;
	}
}
