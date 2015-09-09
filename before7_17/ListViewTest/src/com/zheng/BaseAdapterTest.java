package com.zheng;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseAdapterTest extends Activity implements OnItemClickListener{
	
	private ListView listView = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baseadaptertest);
		
		listView = (ListView) findViewById(R.id.listViewTest);
		BaseAdapterTestAdapter adapter = new BaseAdapterTestAdapter(this);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
		TextView tvName = (TextView) view.findViewById(R.id.tvLarge);
		String name = tvName.getText().toString();
		Toast.makeText(this, name, Toast.LENGTH_LONG).show();
	}

}
