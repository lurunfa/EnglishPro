//选择手机中的文件。To：Student_RWCKDetail返回上传文件的界面。
package com.bupt.english.student_rwck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bupt.english.main.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SDFileExplorer extends Activity {
	ListView listView;
	TextView textView;
	File currentParent;
	File[] currentFiles;
	String path;
	String filename,type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sdcardexplorar);
		type = getIntent().getStringExtra("type");
		Button button = (Button) findViewById(R.id.surepath);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				String filetype = filename.substring(filename.lastIndexOf(".") + 1);
				
				System.out.println(filetype);
				if (type.equals("ppt")) {
					if (filetype.equals("ppt")||filetype.equals("pptx")) {
						intent.putExtra("path", path);
					intent.putExtra("pathName", filename);
					setResult(RESULT_OK,intent);
					finish();
					}else{Toast.makeText(SDFileExplorer.this, "文件格式必须为ppt或pptx", Toast.LENGTH_SHORT).show();}
				}else if(type.equals("word")) {
					if (filetype.equals("doc")||filetype.equals("docx")) {
						intent.putExtra("path", path);
					intent.putExtra("pathName", filename);
					setResult(RESULT_OK,intent);
					finish();
					}else{Toast.makeText(SDFileExplorer.this, "文件格式必须为doc或docxs", Toast.LENGTH_SHORT).show();}
				}else if(type.equals("video")) {
					if (filetype.equals("mp4")||filetype.equals("3gp")) {
						intent.putExtra("path", path);
					intent.putExtra("pathName", filename);
					setResult(RESULT_OK,intent);
					finish();
					}else{Toast.makeText(SDFileExplorer.this, "文件格式必须为3gp或mp4", Toast.LENGTH_SHORT).show();}
				}else if(type.equals("audio")) {
					if (filetype.equals("mp3")||filetype.equals("wma")) {
						intent.putExtra("path", path);
					intent.putExtra("pathName", filename);
					setResult(RESULT_OK,intent);
					finish();
					}else{Toast.makeText(SDFileExplorer.this, "文件格式必须为mp3或wma", Toast.LENGTH_SHORT).show();}
				}
				
			}
		});
		listView = (ListView) findViewById(R.id.list_sd);
		textView = (TextView) findViewById(R.id.path);
		String sd = Environment.getExternalStorageDirectory().getAbsolutePath();
		//System.out.println(sd);
		File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
		if (root.exists()) {
			currentParent = root;
			currentFiles = root.listFiles();
			//System.out.println(currentFiles.length);
			inflateListView(currentFiles);
	}else{Toast.makeText(SDFileExplorer.this, "请插入sdcard", Toast.LENGTH_SHORT).show();}
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (currentFiles[position].isFile()) {
					try {
						path = currentFiles[position].getCanonicalPath();
						//System.out.println(path);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				File[] tmp = currentFiles[position].listFiles();
				if (tmp == null || tmp.length == 0) {
					textView.setText("当前选择文件为:"+currentFiles[position].getName());
					filename = currentFiles[position].getName();
					//System.out.println(filename);
				} else {
					currentParent = currentFiles[position];
					currentFiles = tmp;
					inflateListView(currentFiles);
				}
			}
		});
		Button back = (Button) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					if (!currentParent.getCanonicalPath()
							.equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
						currentParent = currentParent.getParentFile();
						currentFiles = currentParent.listFiles();
						inflateListView(currentFiles);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}

	private void inflateListView(File[] files) {
		//System.err.println(files.length);
		// TODO Auto-generated method stub
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < files.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			if (files[i].isDirectory()) {
				listItem.put("icon", R.drawable.folder);
			} else {
				listItem.put("icon", R.drawable.file);
			}
			listItem.put("fileName", files[i].getName());
			listItems.add(listItem);
		}
		SimpleAdapter adapter = new SimpleAdapter(SDFileExplorer.this,
				listItems, R.layout.sdcared_line, new String[] { "icon",
						"fileName" }, new int[] { R.id.icon, R.id.file_name });
		listView.setAdapter(adapter);
		try {
			textView.setText("当前路径为:"+currentParent.getCanonicalPath());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
