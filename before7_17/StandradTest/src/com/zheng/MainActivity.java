package com.zheng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BaseActivity.addActivity(this);
		System.out.println("MainActivity TaskId : " + getTaskId());
//		System.out.println("MainActivity this : " + this);
		findViewById(R.id.btnStartSecondActivity).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//				startActivity(intent);
				SecondActivity.startActivity(MainActivity.this, "zheng");
			}
		});
	}

	@Override
	protected void onDestroy() {
		BaseActivity.deleteActivity(this);
		super.onDestroy();
	}
}
