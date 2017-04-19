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

public class ClassAdapter extends ArrayAdapter<AppClass> {

	private int resourceId;
	public ClassAdapter(Context context, int textViewResourceId, List<AppClass> objects){
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppClass classNum = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView class_num = (TextView) view.findViewById(R.id.class_num);
		class_num.setText(classNum.getClassName());
		return view;
	}
}
