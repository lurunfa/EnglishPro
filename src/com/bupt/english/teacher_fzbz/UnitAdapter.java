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

public class UnitAdapter extends ArrayAdapter<AppClass> {
	private int resourceId;

	public UnitAdapter(Context context, int textViewresourceId,List<AppClass> objects) {
		super(context, textViewresourceId,objects);
		resourceId = textViewresourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AppClass unitApp = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView textView = (TextView) view.findViewById(R.id.unit_name);
		textView.setText(unitApp.getUnitName().substring(4));
		return view;
	}

}
