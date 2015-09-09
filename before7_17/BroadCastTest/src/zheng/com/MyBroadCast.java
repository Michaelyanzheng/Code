package zheng.com;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent intent) {
		System.out.println(intent.getStringExtra("zheng"));
		System.out.println("onReceive");
		Toast.makeText(arg0, "onReceive", Toast.LENGTH_LONG).show();
	}

}
