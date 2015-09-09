package zheng.com;

import java.io.File;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.btnStartActivity).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent();
//				intent.setComponent(new ComponentName("zheng.com", "zheng.com.Aty1"));
//				startActivity(intent);
				
//				Intent intent = new Intent("zheng.com.intent.action.Aty1");
//				startActivity(intent);
				
				File file = new File("/mnt/sdcard/1.png");
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "image/*");
				startActivity(intent);
				
			}
		});
		
		findViewById(R.id.btnTelPhone).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("tel:10086"));
				startActivity(intent);
				
			}
		});
		findViewById(R.id.btnWeb).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.baidu.com"));
				startActivity(intent);
			}
		});
	}

}








