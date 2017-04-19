package com.bupt.english.teacher_fzbz;

import java.util.List;

import com.bupt.english.main.AppClass;
import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TeacherFZBZAdapter extends ArrayAdapter<AppClass> {
	private int resourceId;

	public TeacherFZBZAdapter(Context context, int resource, List<AppClass> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		AppClass app = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		ImageView image = (ImageView) view.findViewById(R.id.show_image);
		image.setImageResource(app.getimageId());

		TextView text = (TextView) view.findViewById(R.id.show_text);
		text.setText(app.getBackData());
		text.setHint(app.getHint());
		return view;
	}

}
