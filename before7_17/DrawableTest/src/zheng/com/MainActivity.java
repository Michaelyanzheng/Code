package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ImageView imageView;
	private Boolean boolView = true;
	
	private Button btnPress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		imageView = (ImageView) findViewById(R.id.imageView);
//		imageView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				if (boolView) {
//					imageView.setImageResource(R.drawable.a2);
//				}else {
//					imageView.setImageResource(R.drawable.a1);
//				}
//				
//				boolView = !boolView;
//			}
//		});
		
		btnPress = (Button) findViewById(R.id.button3);
		btnPress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(MainActivity.this, "btnPress", Toast.LENGTH_LONG).show();
			}
		});
		
	}
}
