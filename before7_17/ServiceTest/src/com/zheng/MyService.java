package com.zheng;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("onBind");
		return myBinder;
	}
	
	public MyBinder myBinder = new MyBinder();
	
	class MyBinder extends Binder{
		
		public MyService getMyService(){
			return MyService.this;
		}
		
	}
	

	@Override
	public void onCreate() {
		super.onCreate();
		
		System.out.println("onCreate");
	}

	@Override
	public void onDestroy() {
		System.out.println("onDestroy");
		super.onDestroy();
	}
	
	private Timer timer = null;
	private TimerTask timerTask = null;
	private int count = 0;
	
	public void startTime(){
		
		if (timer == null) {
			timer = new Timer();
			timerTask = new TimerTask() {
				
				@Override
				public void run() {
					count++;
					System.out.println(count);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			timer.schedule(timerTask, 1000, 1000);
		}
	} 
	
	public void stopTime(){
		
		if (timer != null) {
			timerTask.cancel();
			timer.cancel();
			timer = null;
			timerTask = null;
		}
	}
	
	public int getCurrentNumber(){
		return this.count;
	}
	
	
	
	
	
	
	
	
	

}
