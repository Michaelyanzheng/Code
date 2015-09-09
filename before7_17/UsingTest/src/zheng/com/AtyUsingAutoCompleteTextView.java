package zheng.com;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AtyUsingAutoCompleteTextView extends Activity {

	private AutoCompleteTextView autoCompleteTextView = null;
	
	private MultiAutoCompleteTextView multiAutoCompleteTextView = null;
	
	private ArrayAdapter<String> adapter = null;
	
	private ArrayAdapter<String> multiAdapter = null;
	
	private String [] data = new String[]{
			"zheng",
			"zhengshou",
			"shouli",
			"shou",
			"michael",
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_autocompletetextview);
		
		autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
		adapter = new ArrayAdapter<String>(this, R.layout.aty_autocompletetextview_cell);
		adapter.add("zhengmichael");
		adapter.add("zheng");
		adapter.add("shou");
		adapter.add("shouli");
		autoCompleteTextView.setAdapter(adapter);
		
		multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
		multiAdapter = new ArrayAdapter<String>(this, R.layout.aty_autocompletetextview_cell, data);
		multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		multiAutoCompleteTextView.setAdapter(multiAdapter);
		
		
	}
}
