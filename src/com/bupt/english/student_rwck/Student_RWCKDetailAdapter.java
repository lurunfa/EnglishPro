package com.bupt.english.student_rwck;

import java.util.List;

import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Student_RWCKDetailAdapter extends ArrayAdapter<StudentApp> {
	private int resourceId;

	public Student_RWCKDetailAdapter(Context context, int resource,
			List<StudentApp> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		StudentApp app = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		ImageView image = (ImageView) view.findViewById(R.id.show_image);
		TextView text = (TextView) view.findViewById(R.id.show_ask);
		image.setImageResource(app.getImageId());
		text.setText(app.getAskText());
		text.setHint(app.getHint());
		return view;
	}

}
