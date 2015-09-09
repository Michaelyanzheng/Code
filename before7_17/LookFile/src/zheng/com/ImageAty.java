package zheng.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageAty extends Activity {
	
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.image_aty);
		imageView = (ImageView) findViewById(R.id.imageView);
		
		String dir = getIntent().getStringExtra("dir");
		File dirFile = new File(dir);
		
		try {
			FileInputStream fileInputStream = new FileInputStream(dirFile);
			
			Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
			imageView.setImageBitmap(bitmap);
			
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}














