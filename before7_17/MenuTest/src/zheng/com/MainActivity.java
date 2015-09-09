package zheng.com;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		
		case R.id.showDialog:
			new AlertDialog.Builder(this).setTitle("Tile zheng").setMessage("zai michael").setPositiveButton("关闭", null).setNegativeButton("取消", null).show();
			break;

		case R.id.showToast:
			
			Toast.makeText(this, "tOAST", Toast.LENGTH_LONG).show();
			
			break;
		}
		return true;
	}
}
