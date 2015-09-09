package com.zheng;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ListView listView = null;
	private ArrayAdapter<String> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView);
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		adapter = new ArrayAdapter<String>(this, R.layout.listview1);
		adapter.add("zheng");
		adapter.add("shou");
		adapter.add("zheng");
		listView.setAdapter(adapter);
	}

}
