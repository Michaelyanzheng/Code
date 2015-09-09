package zheng.com;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	
	private ArrayAdapter<ListCell> adapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		adapter = new ArrayAdapter<ListCell>(this, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		
		adapter.add(new ListCell(this, "Group Button", new Intent(this,AtyUsingGroupButton.class)));
		adapter.add(new ListCell(this, "Check Button", new Intent(this,AtyUsingCheckButton.class)));
		adapter.add(new ListCell(this, "Date Picker", new Intent(this,AtyUsingDatePicker.class)));
		adapter.add(new ListCell(this, "Time Picker", new Intent(this,AtyUsingTimerPicker.class)));
		adapter.add(new ListCell(this, "Spring", new Intent(this,AtyUsingSping.class)));
		adapter.add(new ListCell(this, "AutoCompleteTextView", new Intent(this,AtyUsingAutoCompleteTextView.class)));
		adapter.add(new ListCell(this, "ProgressBar", new Intent(this,AtyUsingProgressBar.class)));
		adapter.add(new ListCell(this, "SeekBar", new Intent(this,AtyUsingSeekingBar.class)));
		adapter.add(new ListCell(this, "GridView", new Intent(this,AtyUsingGridView.class)));
		adapter.add(new ListCell(this, "ProgressDialog", new Intent(this,AtyUsingProgressDialog.class)));
		adapter.add(new ListCell(this, "Notification", new Intent(this,AtyUsingNotification.class)));
		adapter.add(new ListCell(this, "ScrollView", new Intent(this,AtyUsingScrollView.class)));
		adapter.add(new ListCell(this, "RatingBar", new Intent(this,AtyUsingRatingBar.class)));
		adapter.add(new ListCell(this, "ImageSwitch", new Intent(this,AtyUsingImageSwitch.class)));
		adapter.add(new ListCell(this, "ImageView", new Intent(this,AtyUsingImageView.class)));
		adapter.add(new ListCell(this, "Gallery", new Intent(this,AtyUsingGallery.class)));
		adapter.add(new ListCell(this, "EditText", new Intent(this,AtyUsingEditText.class)));
		
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		adapter.getItem(position).startActivity();
		super.onListItemClick(l, v, position, id);
	}
}
