package zheng.com;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class AtyUsingDatePicker extends Activity {

	private Button btnDatePicker = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_datepicker);
		
		Config.year = 2015;
		Config.month = 6;
		Config.day = 2;
		
		btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
		btnDatePicker.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new DatePickerDialog(AtyUsingDatePicker.this, 
						DatePickerDialog.THEME_DEVICE_DEFAULT_DARK, 
						new DatePickerDialog.OnDateSetListener() {
							
							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear,
									int dayOfMonth) {
								Config.year = year;
								Config.month = monthOfYear;
								Config.day = dayOfMonth;
								
								btnDatePicker.setText(year + " : " + ++monthOfYear + " : " + dayOfMonth);
							}
						}, Config.year, Config.month, Config.day).show();
			}
		});
	}
}
