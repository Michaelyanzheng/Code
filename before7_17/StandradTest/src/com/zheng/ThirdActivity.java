package com.zheng;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdactivity);
		System.out.println("ThirdActivity TaskId : " + getTaskId());
		BaseActivity.addActivity(this);
		
		findViewById(R.id.btnFinishAllActivity).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BaseActivity.deleteAllActivity();
			}
		});
		
	}
	@Override
	protected void onDestroy() {
		BaseActivity.deleteActivity(this);
		super.onDestroy();
	}
	@Override
	public void onBackPressed() {
		System.out.println("ThirdActivity onBackPressed");
		super.onBackPressed();
	}
}
