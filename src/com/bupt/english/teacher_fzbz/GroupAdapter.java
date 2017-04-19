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

public class GroupAdapter extends ArrayAdapter<AppClass> {
	private int resourceId;

	public GroupAdapter(Context context, int textViewResourceId,
			List<AppClass> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		AppClass appClass = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView showName = (TextView) view.findViewById(R.id.show_name);
		TextView showGroup = (TextView) view.findViewById(R.id.show_group);
		showName.setText(appClass.getname());
		showGroup.setText(appClass.getgroup());
		return view;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
