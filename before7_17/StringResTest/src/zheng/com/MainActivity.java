package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String str = this.getResources().getString(R.string.zheng);
		System.err.println(str);
		
		textView = (TextView) findViewById(R.id.textView);
		
		textView.setText(R.string.zheng);
		
		
	}

}
