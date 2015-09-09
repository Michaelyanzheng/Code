package zheng.com;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class AtyUsingTimerPicker extends Activity {
	
	private Button btnTimerPicker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_timer_picker);
		
		Config.hour = 10;
		Config.minute = 30;
		
		btnTimerPicker = (Button) findViewById(R.id.btnTimerPicker);
		btnTimerPicker.setText(String.format("%d:%d", Config.hour,Config.minute));
		btnTimerPicker.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new TimePickerDialog(AtyUsingTimerPicker.this,
						TimePickerDialog.THEME_HOLO_DARK, 
						new TimePickerDialog.OnTimeSetListener() {
							
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								Config.hour = hourOfDay;
								Config.minute = minute;
								btnTimerPicker.setText(String.format("%s:%s", forString(hourOfDay),forString(minute)));
							}
						}, Config.hour, Config.minute, true).show();
			}
		});
		
	}
	
	private String forString(int i){
		return i>=10?i+"":"0"+i;
	}

}
