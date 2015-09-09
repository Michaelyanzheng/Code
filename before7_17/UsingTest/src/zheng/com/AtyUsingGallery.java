package zheng.com;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Gallery;

public class AtyUsingGallery extends Activity {
	
	private Gallery gallery;
	
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_gallery);
		
		gallery = (Gallery) findViewById(R.id.gallery);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		for (int i = 0; i < 10; i++) {
			adapter.add("zheng : " + i);
		}
		
		gallery.setAdapter(adapter);
	}

}
