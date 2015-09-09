package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private ListView listView;
	
	private MyAdapter<String> adapter;
	
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView) findViewById(R.id.list);
		
		adapter = new MyAdapter<String>(this,android.R.layout.simple_list_item_1) {
			
			@Override
			public void initItemCell(int position, View convertView, ViewGroup parent) {

				((TextView)(convertView)).setText(getItem(position));	
					
			}
		};
		
		listView.setAdapter(adapter);
		
		for (index = 0; index < 5; index++) {
			adapter.addList("zheng --- " + index);
		}
		
		findViewById(R.id.btnAdd).setOnClickListener(this);
		findViewById(R.id.btnDel).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		
		case R.id.btnAdd:
			
			adapter.addList("zheng --- " + index);
			index++;
			
			break;
			
		case R.id.btnDel:
			
			adapter.removeLast();
			if (index >= 1) {
				
				index--;
			}
			
			break;

		default:
			break;
		}
	}
}
