package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AtyUsingImageView extends Activity {
	
	private ImageView imageView;
	private boolean isCheck = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_imageview);
		
		imageView = (ImageView) findViewById(R.id.imageView);
		imageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				isCheck = !isCheck;
				switchImage();
			}
		});
		switchImage();
	}
	
	
	private void switchImage(){
		if (isCheck == true) {
			imageView.setImageResource(R.drawable.b2);
		}else {
			imageView.setImageResource(R.drawable.b1);
		}
	}

}













