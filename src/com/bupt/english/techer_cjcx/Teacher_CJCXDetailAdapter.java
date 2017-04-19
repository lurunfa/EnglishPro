package com.bupt.english.techer_cjcx;

import java.util.List;

import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Teacher_CJCXDetailAdapter extends ArrayAdapter<TeacherApp> {
	private int resourceId;

	public Teacher_CJCXDetailAdapter(Context context, int resource, List<TeacherApp> list) {
		super(context, resource, list);
		// TODO Auto-generated constructor stub
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TeacherApp app =  getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView textView = (TextView) view.findViewById(R.id.show_backdata);
		textView.setText(app.getBackData());
		return view;
	}

}
