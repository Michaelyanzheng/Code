package com.zheng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondactivity);
		BaseActivity.addActivity(this);
		System.out.println("SecondActivity TaskId : " + getTaskId());
		System.out.println(getIntent().getStringExtra("show"));
		
//		System.out.println("SecondActivity this : " + this);
		findViewById(R.id.btnStartThird).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public static void startActivity(Context context,String show){
		Intent intent = new Intent(context,SecondActivity.class);
		intent.putExtra("show", show);
		context.startActivity(intent);
		
		
	}
	@Override
	protected void onDestroy() {
		BaseActivity.deleteActivity(this);
		super.onDestroy();
	}
}
