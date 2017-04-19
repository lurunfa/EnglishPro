package com.bupt.english.techer_cjcx;

import java.util.List;

import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CJCX_StudentDetailAdapter extends ArrayAdapter<TeacherApp> {
	int resourceId;
	public CJCX_StudentDetailAdapter(Context context, int resource,
			List<TeacherApp> objects) {
		super(context, resource, objects);
		resourceId = resource;
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TeacherApp app = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView textView = (TextView) view.findViewById(R.id.show_cjcxdata);
		textView.setText(app.getdata());
		return view;
	}

}
