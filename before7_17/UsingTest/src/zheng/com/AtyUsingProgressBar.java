package zheng.com;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AtyUsingProgressBar extends Activity {
	
	private ProgressBar progressBar = null;
	
	private Timer timer = null;
	
	private TimerTask timerTask = null;
	
	private int progress = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_progressbar);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		
		progressBar.setMax(100);
		progressBar.setProgress(0);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		startTask();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		stopTask();
	}
	
	private void startTask(){
		if (timer == null) {
			
			timer = new Timer();
			timerTask = new TimerTask() {
				
				@Override
				public void run() {
					progress++;
					progressBar.setProgress(progress);
				}
			};
			timer.schedule(timerTask, 1000, 1000);
		}
	}
	
	private void stopTask(){
		
		if (timer != null) {
			
			timerTask.cancel();
			timer.cancel();
			timer = null;
			timerTask = null;
		}
	}

}















