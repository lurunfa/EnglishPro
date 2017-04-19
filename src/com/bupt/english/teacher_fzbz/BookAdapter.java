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

public class BookAdapter extends ArrayAdapter<AppClass> {
	private int resourceId;

	public BookAdapter(Context context, int textViewResourceId,List<AppClass> objects) {
		super(context,textViewResourceId,objects);
		// TODO Auto-generated constructor stub
		resourceId = textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		AppClass book = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView bookName = (TextView) view.findViewById(R.id.book_name);
		bookName.setText(book.getBookName());
		return view;
	}

}
