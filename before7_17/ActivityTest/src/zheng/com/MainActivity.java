package zheng.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnStartActvity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnStartActvity = (Button) findViewById(R.id.btnStartActivity);

		findViewById(R.id.btnStartActivity).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//				Intent intent = new Intent(MainActivity.this,Aty1.class);
				//				startActivity(intent);

				Intent intent = new Intent(MainActivity.this,Aty1.class);
				startActivityForResult(intent, 0);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (resultCode) {
		case 1:
			btnStartActvity.setText(data.getStringExtra("zheng"));
			break;

		default:
			break;
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
