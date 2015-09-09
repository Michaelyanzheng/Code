package zheng.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aty1 extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty1);
		findViewById(R.id.btnCallBack).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("android.intent.action.MAIN");
				intent.putExtra("zheng", "michael");
				setResult(1, intent);
				finish();
			}
		});
	}
}
