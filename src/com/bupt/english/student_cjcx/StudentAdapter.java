package com.bupt.english.student_cjcx;
/*
 * 学生任务的listview的适配器
 * 
 */
import java.util.List;

import com.bupt.english.main.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StudentAdapter extends ArrayAdapter<Student> {
	private int resourceId;

	public StudentAdapter(Context context, int resource, List<Student> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		/*
		 * 可以用ViewHolder进行优化，是下拉页面过度平滑
		 */
		Student student = getItem(position);
		View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		TextView text = (TextView) view.findViewById(R.id.show_studentgrade_itemtask);
		text.setText("任务："+student.gettask());
		TextView sign = (TextView)view.findViewById(R.id.show_studentgrade_sign);
		sign.setText("已评分");
		return view;
		
		
	}

}
