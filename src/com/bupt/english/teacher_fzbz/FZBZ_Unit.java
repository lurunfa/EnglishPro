package com.bupt.english.teacher_fzbz;
/*
 * 选择单元页面
 */
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bupt.english.main.AppClass;
import com.bupt.english.main.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class FZBZ_Unit extends Activity{
	static final int SHOW_RESPONSE = 0;
	String bookNum;
	
	List<AppClass> unitList = new ArrayList<AppClass>();

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(com.bupt.english.main.R.layout.fzbz_unit);
		Intent intent = getIntent();
		String book = intent.getStringExtra("bookNum");
		init(book);
		UnitAdapter adapter = new UnitAdapter(FZBZ_Unit.this, R.layout.unit_item, unitList);
		ListView listView = (ListView) findViewById(R.id.lv_unit);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent();
				intent1.putExtra("unitName", unitList.get(position).getUnitName().substring(4));
				intent1.putExtra("unitId", unitList.get(position).getUnitName().substring(0,4));
				setResult(RESULT_OK, intent1);
				finish();
				
			}
		});
		
		
	
		

	}
	//这个内置的原因是之前对于报头问题一直没有解决后来为了不拖延时间就直接内定
	private void init(String book) {
		// TODO Auto-generated method stub
	if (book.equals("01")) {
		AppClass app1 = new AppClass();
		AppClass app2 = new AppClass();
		AppClass app3 = new AppClass();
		AppClass app4 = new AppClass();
		AppClass app5 = new AppClass();
		AppClass app6 = new AppClass();
		AppClass app7 = new AppClass();
		app1.setUnitName("0101第一章 问候与介绍");
		app2.setUnitName("0102第二章 校园生活与社交聚会");
		app3.setUnitName("0103第三章 打电话与购物");
		app4.setUnitName("0104第四章 求职面试与就业");
		app5.setUnitName("0105第五章 旅游与名胜");
		app6.setUnitName("0106第六章 动物与金钱");
		app7.setUnitName("0107第七章 媒体与广告");
		unitList.add(app1);
		unitList.add(app2);
		unitList.add(app3);
		unitList.add(app4);
		unitList.add(app5);
		unitList.add(app6);
		unitList.add(app7);
	}else if (book.equals("02")) {
		AppClass app1 = new AppClass();
		AppClass app2 = new AppClass();
		AppClass app3 = new AppClass();
		AppClass app4 = new AppClass();
		AppClass app5 = new AppClass();
		AppClass app6 = new AppClass();
		AppClass app7 = new AppClass();
		app1.setUnitName("0201第一章 运动与饮食");
		app2.setUnitName("0202第二章 天气与音乐");
		app3.setUnitName("0203第三章 健康与商务");
		app4.setUnitName("0204第四章 时尚与社会");
		app5.setUnitName("0205第五章 梦想与灾难");
		app6.setUnitName("0206第六章 名人与计算机网络");
		app7.setUnitName("0207第七章 美德与文化");
		unitList.add(app1);
		unitList.add(app2);
		unitList.add(app3);
		unitList.add(app4);
		unitList.add(app5);
		unitList.add(app6);
		unitList.add(app7);
	}else if (book.equals("03")) {
		AppClass app1 = new AppClass();
		AppClass app2 = new AppClass();
		AppClass app3 = new AppClass();
		AppClass app4 = new AppClass();
		AppClass app5 = new AppClass();
		AppClass app6 = new AppClass();
		AppClass app7 = new AppClass();
		app1.setUnitName("0301第一章 父母与巧合");
		app2.setUnitName("0302第二章 勇气与婚姻");
		app3.setUnitName("0303第三章 年轻人与压力");
		app4.setUnitName("0304第四章 商界与环境");
		app5.setUnitName("0305第五章 欧元与电影产业");
		app6.setUnitName("0306第六章 惯用左手现象与生物多样性");
		app7.setUnitName("0307第七章 发明与女性");
		unitList.add(app1);
		unitList.add(app2);
		unitList.add(app3);
		unitList.add(app4);
		unitList.add(app5);
		unitList.add(app6);
		unitList.add(app7);
	}else 	if (book.equals("04")) {
		AppClass app1 = new AppClass();
		AppClass app2 = new AppClass();
		AppClass app3 = new AppClass();
		AppClass app4 = new AppClass();
		AppClass app5 = new AppClass();
		AppClass app6 = new AppClass();
		AppClass app7 = new AppClass();
		app1.setUnitName("0401第一章 同一个世界与反吸烟");
		app2.setUnitName("0402第二章 记忆与文化差异");
		app3.setUnitName("0403第三章 友谊与成功");
		app4.setUnitName("0404第四章 财富与战争");
		app5.setUnitName("0405第五章 衰老与家庭教学");
		app6.setUnitName("0406第六章 民意调查与真人秀");
		app7.setUnitName("0407第七章 生活与犯罪");
		unitList.add(app1);
		unitList.add(app2);
		unitList.add(app3);
		unitList.add(app4);
		unitList.add(app5);
		unitList.add(app6);
		unitList.add(app7);
	}
		
	}
}