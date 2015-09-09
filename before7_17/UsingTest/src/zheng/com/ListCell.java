package zheng.com;

import android.content.Context;
import android.content.Intent;

public class ListCell {
	
	private Context context;
	private String name;
	private Intent intent;
	
	public ListCell(Context context,String name,Intent intent) {
		this.context = context;
		this.name = name;
		this.intent = intent;
	}
	
	public void startActivity(){
		context.startActivity(intent);
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Intent getIntent() {
		return intent;
	}

	public void setIntent(Intent intent) {
		this.intent = intent;
	}

}
