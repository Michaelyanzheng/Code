package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	
	private RelativeLayout relativeLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		relativeLayout = (RelativeLayout) findViewById(R.id.atyMain);
		relativeLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case  R.id.atyMain:
			Toast toast = Toast.makeText(this, "zheng", Toast.LENGTH_LONG);
			
			LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.aty_toast, null);
			ImageView imageView = (ImageView) linearLayout.findViewById(R.id.ivToast);
			imageView.setImageResource(R.drawable.ic_launcher);
			TextView textView = (TextView) linearLayout.findViewById(R.id.tvToast);
			textView.setText("dddddddddd");
			
			
			toast.setView(linearLayout);
			toast.setGravity(Gravity.AXIS_PULL_BEFORE, (int)view.getX(), (int)view.getY());
			toast.setDuration(1);
			toast.show();
			break;

		default:
			break;
		}
	}

}








