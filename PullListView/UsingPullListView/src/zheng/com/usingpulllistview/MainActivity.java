package zheng.com.usingpulllistview;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private PullToRefreshListView lv;
	private ArrayAdapter<String> arrayAdapter;
	private List<String> list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (PullToRefreshListView) findViewById(R.id.mylv);
		
		list.add("michael");
		list.add("shou");
		
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
		lv.setAdapter(arrayAdapter);
		
		lv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				
				new AsyncTask<Void, Void, Void>(){

					@Override
					protected Void doInBackground(Void... params) {
						
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						return null;
					}
					
					@Override
					protected void onPostExecute(Void result) {
						
						List<String> addList = new ArrayList<String>();
						addList.add("shouli");
						addList.add("zheng michael");
						
						list.addAll(0, addList);
						arrayAdapter.notifyDataSetChanged();
						lv.onRefreshComplete();
						super.onPostExecute(result);
					}
					
				}.execute();
			}
			
		});
	}
}













