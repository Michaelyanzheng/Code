package zheng.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class AtyUsingGroupButton extends Activity {
	
	private RadioButton rbYes;
	private Button btnSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_group_button);
		
		rbYes = (RadioButton) findViewById(R.id.radioyes);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (rbYes.isChecked()) {
					new AlertDialog.Builder(AtyUsingGroupButton.this).setTitle("回答").setMessage("回答正确").setPositiveButton("关闭", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(AtyUsingGroupButton.this, "回答正确", Toast.LENGTH_SHORT).show();
						}
					}).show();
				}else{
					new AlertDialog.Builder(AtyUsingGroupButton.this).setTitle("回答").setMessage("回答错误").setPositiveButton("关闭", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(AtyUsingGroupButton.this, "回答错误", Toast.LENGTH_SHORT).show();
						}
					}).show();
				}
			}
		});
	}

}
