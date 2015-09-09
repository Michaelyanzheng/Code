package com.zheng;

import android.app.ListActivity;
import android.os.Bundle;

public class Base extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base);
		setListAdapter(new BaseAdapterTestAdapter(this));
	}

}
