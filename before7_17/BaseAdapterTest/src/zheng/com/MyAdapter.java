package zheng.com;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyAdapter<T> extends BaseAdapter {
	
	private List<T> list = new ArrayList<T>();
	
	private Context context;
	private int resId;
	
	public MyAdapter(Context context,int resId){
		
		this.context = context;
		this.resId = resId;
	}
	
	public Context getContext(){
		return this.context;
	}
	
	public int getResId(){
		return this.resId;
	}
	
	public void addList(T item){
		list.add(item);
		notifyDataSetChanged();
	}
	
	public void removeList(int position){
		if (position >= 0) {
			
			list.remove(position);
			notifyDataSetChanged();
		}
	}
	
	public void removeLast(){
		removeList(getCount()-1);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public T getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(getResId(), null);
		}
		
		initItemCell(position, convertView, parent);
		
		return convertView;
	}
	
	public abstract void initItemCell(int position, View convertView, ViewGroup parent);

}
