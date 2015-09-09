package zheng.com;

import android.R.integer;
import android.app.Activity;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity {
	
	private RelativeLayout relativeLayout;
	
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		relativeLayout = (RelativeLayout) findViewById(R.id.container);
		imageView = (ImageView) findViewById(R.id.imageView);

		relativeLayout.setOnTouchListener(new View.OnTouchListener() {
			
			int lastDistance = -1;
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					
					System.out.println("action down");
					
					break;
					
				case MotionEvent.ACTION_MOVE:
//					RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
//					layoutParams.leftMargin = (int) event.getX();
//					layoutParams.topMargin = (int) event.getY();
//					
//					imageView.setLayoutParams(layoutParams);
//					
//					System.out.println(String.format("x:%f---y:%y", view.getX(),view.getY()));
					
					
//					System.out.println("pointer count : " + event.getPointerCount());
					
//					System.out.println(String.format("x1:%f y1:%f x2:%f y2:%f", event.getX(0),event.getY(0),event.getX(1),event.getY(2)));
					
					System.out.println("action move");
					System.out.println(event.getPointerCount());
					
					RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
					
					if (event.getPointerCount() >=2) {
						int xDistance = (int) (event.getX(0) - event.getX(1));
						int yDistance = (int) (event.getY(0) - event.getY(1));
						int currentDistance = (int) Math.sqrt(xDistance*xDistance + yDistance*yDistance);
						if (lastDistance <= 0) {
							lastDistance = currentDistance;
							System.out.println(lastDistance+"-------");
						}else {
							if (lastDistance-currentDistance > 5) {
								layoutParams.width = (int) (imageView.getWidth() * 0.9);
								layoutParams.height = (int) (imageView.getHeight() * 0.9);
								System.out.println("缩小");
							}
							if (currentDistance - lastDistance > 5) {
								layoutParams.width = (int) (imageView.getWidth() * 1.1);
								layoutParams.height = (int) (imageView.getHeight() * 1.1);
								System.out.println("变大");
							}
							lastDistance = currentDistance;
							imageView.setLayoutParams(layoutParams);
						}
					}
					
					break;
					
				case MotionEvent.ACTION_UP:
//					System.out.println("action up");
					
					break;
					
				default :
					break;

				}
				return true;
			}
		});
	}

}
