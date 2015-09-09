package zheng.com;

import java.io.File;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	private ArrayAdapter<FileMode> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String dirFile = getIntent().getStringExtra("dir");
		File file;
		
		if (dirFile == null) {
			
			file = new File("/");
		}else {
			file = new File(dirFile);
		}
		
		File[] dir = file.listFiles();	
		
		if (dir != null) {
			
			adapter = new ArrayAdapter<FileMode>(this, android.R.layout.simple_list_item_1);
			
			for(File f : dir){
				adapter.add(new FileMode(f));
			}
			setListAdapter(adapter);
		}else {
			new AlertDialog.Builder(this).setTitle("提示").setMessage("这是空文件夹").setPositiveButton("关闭", null).show();
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		FileMode fileMode = adapter.getItem(position);
		
		if (fileMode.getFile().isDirectory()) {
			
			Intent intent = new Intent(this,MainActivity.class);
			intent.putExtra("dir", fileMode.getFile().getAbsolutePath());
			startActivity(intent);
			
		}else {
			String type = fileMode.getFile().getName().substring(fileMode.getFile().getName().length()-3);
			if (type.equals("txt")) {
				Intent intent = new Intent(this,TxtAty.class);
				intent.putExtra("dir", fileMode.getFile().getAbsolutePath());
				startActivity(intent);
			}else{
				if (type.equals("png") || type.equals("jpg")) {
					Intent intent = new Intent(this,ImageAty.class);
					intent.putExtra("dir", fileMode.getFile().getAbsolutePath());
					startActivity(intent);
				}
			}
			
		}
		
		super.onListItemClick(l, v, position, id);
	}

}
