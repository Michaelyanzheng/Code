package zheng.com;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String [] arrString = getResources().getStringArray(R.array.arrZheng);
		
		for (int i = 0; i < arrString.length; i++) {
			System.out.println(arrString[i]);
		}
	}

}
