package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	private long lastTime = 0;
	
	@Override
	public void onBackPressed() {
		
		if (lastTime == 0) {
			
			Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
			lastTime = System.currentTimeMillis();
		}else{
			
			if ((System.currentTimeMillis() - lastTime) < 1000) {
				
				finish();
				
			}else{
				Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
				lastTime = System.currentTimeMillis();
			}
		}
		
	}
}
