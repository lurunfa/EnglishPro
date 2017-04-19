package com.bupt.english.techer_cjcx;

import java.util.List;

import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CJCX_StudentAdapter extends ArrayAdapter<TeacherApp> {
	private int resourceId;

	public CJCX_StudentAdapter(Context context, int resource,
			List<TeacherApp> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TeacherApp app = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView show_name = (TextView) view.findViewById(R.id.show_cjcxstudentname);
		show_name.setText(app.getname());
		TextView show_flag = (TextView) view.findViewById(R.id.show_cjcxstudentevaluate);
		
		if (app.getflag().equals("2")) {
			show_flag.setText("已评价");
		}else {
			show_flag.setText("未评价");
		}
		return view;
	}

}
