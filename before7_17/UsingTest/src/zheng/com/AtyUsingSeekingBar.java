package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class AtyUsingSeekingBar extends Activity {
	
	private SeekBar seekBar = null;
	private TextView tvOut = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_seekbar);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		tvOut = (TextView) findViewById(R.id.tvOut);
		seekBar.setMax(50);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				tvOut.setText(String.format("progress is : %d", progress)+"%");
			}
		});
		
		
	}

}
