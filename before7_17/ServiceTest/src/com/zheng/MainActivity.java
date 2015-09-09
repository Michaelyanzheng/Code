package com.zheng;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener, ServiceConnection{
	
	private Button btnStartService;
	private Button btnStopService;
	
	private Button btnBindService;
	private Button btnUnBindService;
	
	private Button btnStartTime;
	private Button btnStopTime;
	
	private Button btnGetCurrentNumber;
	
	private MyService myService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnStartService = (Button) findViewById(R.id.btnStartService);
		btnStopService = (Button) findViewById(R.id.btnStopService);
		btnStartService.setOnClickListener(this);
		btnStopService.setOnClickListener(this);
		
		btnBindService = (Button) findViewById(R.id.btnBindService);
		btnUnBindService = (Button) findViewById(R.id.btnUnBindService);
		btnBindService.setOnClickListener(this);
		btnUnBindService.setOnClickListener(this);
		
		btnStartTime = (Button) findViewById(R.id.btnStartTime);
		btnStopTime = (Button) findViewById(R.id.btnStopTime);
		btnStartTime.setOnClickListener(this);
		btnStopTime.setOnClickListener(this);
		
		btnGetCurrentNumber = (Button) findViewById(R.id.btnGetCurrentNumber);
		btnGetCurrentNumber.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btnStartService:
			Intent intent = new Intent(this,MyService.class);
			startService(intent);
			break;
			
		case R.id.btnStopService:
			stopService(new Intent(this,MyService.class));
			break;
			
		case R.id.btnBindService:
			
			bindService(new Intent(this,MyService.class),this, Context.BIND_AUTO_CREATE);
			break;
			
		case R.id.btnUnBindService:
			unbindService(this);
			break;
			
		case R.id.btnStartTime:
			myService.startTime();
			break;
			
		case R.id.btnStopTime:
			myService.stopTime();
			break;
			
		case R.id.btnGetCurrentNumber:
//			Toast.makeText(this, myService.getCurrentNumber()+"", Toast.LENGTH_SHORT).show();
			System.out.println("CurrentNumber is : " + myService.getCurrentNumber());
			break;

		default:
			break;
		}
	}

	@Override
	public void onServiceConnected(ComponentName arg0, IBinder binder) {
		
		myService = ((MyService.MyBinder)binder).getMyService();
		
		System.out.println("onServiceConnected");
	}

	@Override
	public void onServiceDisconnected(ComponentName arg0) {
	}

}
