package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class Aty1 extends Activity {
	
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.aty1);
		imageView = new ImageView(this);
		setContentView(imageView);
		imageView.setImageURI(getIntent().getData());
	}

}
