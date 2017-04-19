package com.bupt.english.techer_cjcx;

import java.util.List;

import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Teacher_CJCXAdapter extends ArrayAdapter<TeacherApp> {
	private int resourceId;
	public Teacher_CJCXAdapter(Context context, int resource,
			List<TeacherApp> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TeacherApp app = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		ImageView image = (ImageView) view.findViewById(R.id.image_teachercjcx);
		image.setImageResource(app.getImageId_CJCX());
		TextView textView = (TextView) view.findViewById(R.id.text_teachercjcx);
		textView.setText(app.gettext());
		textView.setHint(app.getHint());
		return view;
		}

}
