package zheng.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class AtyUsingCheckButton extends Activity {
	
	private CheckBox cbWater,cbVegetables,cbRice;
	
	private Button btnSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_checkbutton);
		
		cbWater = (CheckBox) findViewById(R.id.cbWater);
		cbVegetables = (CheckBox) findViewById(R.id.cbVegetable);
		cbRice = (CheckBox) findViewById(R.id.cbRice);
		
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String eat = "";
				if (cbWater.isChecked()) {
					eat = cbWater.getText().toString() + "\n";
				}
				if (cbVegetables.isChecked()) {
					eat = eat + cbVegetables.getText().toString() + "\n";
				}
				if (cbRice.isChecked()) {
					eat = eat + cbRice.getText().toString() + "\n";
				}
				
				new AlertDialog.Builder(AtyUsingCheckButton.this).setTitle("中午吃").setMessage(eat).setPositiveButton("关闭", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(AtyUsingCheckButton.this, "hhhhhhhh", Toast.LENGTH_SHORT).show();
					}
				}).show();
			}
		});
		
	}

}
