package zheng.com;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private ListCell [] list = new ListCell[]{
			
			new ListCell("a1", "dd", R.drawable.a1),
			new ListCell("a2", "ddd", R.drawable.a2),
			new ListCell("a3", "dddd", R.drawable.a3),
			new ListCell("a4", "ddddd", R.drawable.a4),
			new ListCell("a5", "dddd", R.drawable.a5),
			new ListCell("a6", "ddd", R.drawable.a6),
			};
	
	private Context context;
	
	public MyAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return list.length;
	}

	@Override
	public ListCell getItem(int position) {
		return list[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)  {
		
		LinearLayout linearLayout = null;
		Cell cell = null;
		
		if (convertView != null) {
			
			linearLayout = (LinearLayout) convertView;
			cell = (Cell) linearLayout.getTag();
			
		}else{
			
			linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.listcell, null);
			cell = new Cell();
			
			cell.imageView = (ImageView) linearLayout.findViewById(R.id.imageView);
			cell.tvName = (TextView) linearLayout.findViewById(R.id.tvName);
			cell.tvInformation = (TextView) linearLayout.findViewById(R.id.tvInformation);
			linearLayout.setTag(cell);
		}

		 ListCell listCell = list[position];
		 
		 cell.imageView.setImageResource(listCell.getResourceId());
		 cell.tvName.setText(listCell.getName());
		 cell.tvInformation.setText(listCell.getInformation());
		 
		return linearLayout;
	}
	
	private class Cell{
		private ImageView imageView;
		private TextView tvName;
		private TextView tvInformation;
	}

}
