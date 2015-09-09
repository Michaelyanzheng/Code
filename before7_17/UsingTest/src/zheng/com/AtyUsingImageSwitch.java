package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

public class AtyUsingImageSwitch extends Activity {
	
	private ImageSwitcher imageSwitch;
	
	private boolean isSwitch = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_imageswitch);
		
		imageSwitch = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		
		imageSwitch.setFactory(new ImageSwitcher.ViewFactory() {
			
			@Override
			public View makeView() {
				
				return new ImageView(AtyUsingImageSwitch.this);
			}
		});
		
		imageSwitch.setInAnimation(AnimationUtils.loadAnimation(AtyUsingImageSwitch.this, android.R.anim.fade_in));
		imageSwitch.setOutAnimation(AnimationUtils.loadAnimation(AtyUsingImageSwitch.this, android.R.anim.fade_out));
		
		imageSwitch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				isSwitch = !isSwitch;
				switchImage();
			}
		});
		
		switchImage();
	}
	
	private void switchImage(){
		
		if (isSwitch) {
			imageSwitch.setImageResource(R.drawable.b1);
		}else{
			imageSwitch.setImageResource(R.drawable.b2);
		}
	}

}











