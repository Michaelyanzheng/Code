package zheng.com;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AtyUsingProgressDialog extends Activity {
	
	private Button btnProgressLog;
	
	private ProgressDialog progressDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_progressdialog);
		
		btnProgressLog = (Button) findViewById(R.id.btnProgressDialog);
		btnProgressLog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				progressDialog = ProgressDialog.show(AtyUsingProgressDialog.this, "ProgressDialog", "zheng");
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						progressDialog.dismiss();
					}
				}).start();
			}
		});
		
		
	}

}













