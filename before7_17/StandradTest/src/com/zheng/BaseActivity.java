package com.zheng;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class BaseActivity{
	
	private static List<Activity> listActivities = new ArrayList<Activity>();
	
	public static void addActivity(Activity activity){
		listActivities.add(activity);
	}
	
	public static void deleteActivity(Activity activity){
		listActivities.remove(activity);
	}
	
	public static void deleteAllActivity(){
		for (Activity activity : listActivities) {
			activity.finish();
		}
		listActivities.clear();
	}
	

}
