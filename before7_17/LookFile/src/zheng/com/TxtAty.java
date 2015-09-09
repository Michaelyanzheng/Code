package zheng.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TxtAty extends Activity {
	
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.txt_aty);
		
		String dir = getIntent().getStringExtra("dir");
		File file = new File(dir);
		byte [] buffer;
		
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
		    buffer = new byte[fileInputStream.available()];
			fileInputStream.read(buffer);
			textView = (TextView) findViewById(R.id.textView);
			textView.setText(new String(buffer));
			
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
