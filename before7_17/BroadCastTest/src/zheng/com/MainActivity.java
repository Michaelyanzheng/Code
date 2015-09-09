package zheng.com;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	private static final String ACTION = "zheng.com.MyBroadCast";
	private MyBroadCast myBroadCast = new MyBroadCast();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btnBroadCast).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(MainActivity.this,MyBroadCast.class);
//				intent.putExtra("zheng", "michael");
//				sendBroadcast(intent);
				
				Intent intent = new Intent(ACTION);
				intent.putExtra("zheng", "michael");
				sendBroadcast(intent);
			}
		});
		
		findViewById(R.id.btnRegisterBroadCast).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				registerReceiver(myBroadCast, new IntentFilter(ACTION));
			}
		});
		
		findViewById(R.id.btnUnRegisterBroadCast).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unregisterReceiver(myBroadCast);
			}
		});
	}

}
