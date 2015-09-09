package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class AtyUsingGridView extends Activity {
	
	private GridView gridView = null;
	
	private ArrayAdapter<String> adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_gridview);
		gridView = (GridView) findViewById(R.id.gridView);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		
		gridView.setAdapter(adapter);
		
		for (int i = 0; i < 60; i++) {
			adapter.add("zheng" + i);
			
		}
		
		
	}

}
