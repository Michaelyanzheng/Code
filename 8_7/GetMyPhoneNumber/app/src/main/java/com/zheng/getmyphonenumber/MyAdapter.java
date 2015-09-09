package com.zheng.getmyphonenumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 2015/8/7.
 */
public class MyAdapter extends BaseAdapter {

    private List<PhoneInfo> mList = new ArrayList<PhoneInfo>();
    private Context mContext = null;

    public MyAdapter(List<PhoneInfo> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public PhoneInfo getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PhoneInfo phoneInfo = getItem(position);

        ViewHolder viewHolder;

        View view;

        if (convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.cell,null);

            viewHolder = new ViewHolder();
            viewHolder.nameTV = (TextView) view.findViewById(R.id.phoneName);
            viewHolder.phoneTV = (TextView) view.findViewById(R.id.phoneNumber);

            view.setTag(viewHolder);

        }else{

            view = convertView;
            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.nameTV.setText(phoneInfo.getPhoneName());
        viewHolder.phoneTV.setText(phoneInfo.getPhoneNumber());

        return view;
    }

    class ViewHolder{
        TextView nameTV;
        TextView phoneTV;
    }
}
