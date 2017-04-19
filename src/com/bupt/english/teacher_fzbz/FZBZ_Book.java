package com.bupt.english.teacher_fzbz;
/*
 * 选择书号
 */
import java.util.ArrayList;

import java.util.List;

import com.bupt.english.main.AppClass;
import com.bupt.english.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FZBZ_Book extends Activity {
	private List<AppClass> bookList = new ArrayList<AppClass>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fzbz_bookname);
	
				
		initBook();
		BookAdapter adapter = new BookAdapter(FZBZ_Book.this,
				R.layout.book_item, bookList);
		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				AppClass book = bookList.get(position);
				Intent intent = new Intent();
				intent.putExtra("bookNum", book.getBookNum());
				intent.putExtra("bookName", book.getBookName());
				//Log.d("FZBZ", book.getBookNum());
				setResult(RESULT_OK, intent);
				finish();

			}
		});

	}

	private void initBook() {
		// TODO Auto-generated method stub
		AppClass first = new AppClass();
		first.setBookName("大学英语实验教程(第1册)");
		first.setBookNum("01");
		bookList.add(first);
		AppClass second = new AppClass();
		second.setBookName("大学英语实验教程(第2册)");
		second.setBookNum("02");
		bookList.add(second);
		AppClass third = new AppClass();
		third.setBookName("大学英语实验教程(第3册)");
		third.setBookNum("03");
		bookList.add(third);
		AppClass fourth = new AppClass();
		fourth.setBookName("大学英语实验教程(第4册)");
		fourth.setBookNum("04");
		bookList.add(fourth);

	}

}
