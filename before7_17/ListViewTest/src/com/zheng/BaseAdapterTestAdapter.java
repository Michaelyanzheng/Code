package com.zheng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaseAdapterTestAdapter extends BaseAdapter {

	private Lite []lite = new Lite[]{
			new Lite("michael", "michael  1111", R.drawable.a1),
			new Lite("zheng", "zheng  222", R.drawable.a2),
			new Lite("shou", "shou  3333", R.drawable.a3),
	};
	
	private Context context;
	
	public BaseAdapterTestAdapter(Context context) {
		this.context = context;
	}
	
	private Context getContext(){
		return this.context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout linearLayout = null;
		if (convertView != null) {
			linearLayout = (LinearLayout) convertView;
		}else{
			linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.listitem, null);
		}
		ImageView imageView = (ImageView) linearLayout.findViewById(R.id.imageView);
		TextView largeTextView = (TextView) linearLayout.findViewById(R.id.tvLarge);
		TextView smallTextView = (TextView) linearLayout.findViewById(R.id.tvSmall);
		
		Lite lite = getItem(position);
		imageView.setImageResource(lite.getImage());
		largeTextView.setText(lite.getName());
		smallTextView.setText(lite.getInformation());

		return linearLayout;
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public Lite getItem(int position) {
		return lite[position];
	}
	
	@Override
	public int getCount() {
		return lite.length;
	}

}
