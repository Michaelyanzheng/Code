package com.zheng.convenience.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zheng.convenience.R;

/**
 * Created by michael on 2015/9/8.
 */
public class ContactFragment extends ListFragment {

    private ListView mContactListview;
    private MyCursorAdapter mMyCursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        ContentResolver contentProvider = getActivity().getContentResolver();
        Cursor cursor = contentProvider.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null, null);

        mMyCursorAdapter = new MyCursorAdapter(
                getActivity(), R.layout.fragment_contact_item, cursor);

        setListAdapter(mMyCursorAdapter);

        //        new ArrayAdapter<Object>(getActivity(),android.R.layout.simple_list_item_1);

        return view;
    }

    private class MyCursorAdapter extends CursorAdapter {

        private int mViewResourceId = 0;
        private LayoutInflater mLayoutInflater = null;

        public MyCursorAdapter(Context context, int viewResourceId, Cursor c) {
            super(context, c);
            mViewResourceId = viewResourceId;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            ViewHolder viewHolder = new ViewHolder();

            mLayoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = mLayoutInflater.inflate(mViewResourceId, parent, false);

            viewHolder.mPhoneNameTextView = (TextView) view.findViewById(R.id.phoneName_Textview);
            viewHolder.mPhoneNumberTextView = (TextView) view.findViewById(R.id.phoneNumber_TextView);

            view.setTag(viewHolder);

            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {


            ViewHolder viewHolder = (ViewHolder) view.getTag();

            String phoneNumber = cursor.getString(
                    cursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER)
            );

            viewHolder.mPhoneNameTextView.setText(
                    cursor.getString(
                            cursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

            viewHolder.mPhoneNumberTextView.setText(phoneNumber);

        }

        private class ViewHolder {

            private TextView mPhoneNameTextView;

            private TextView mPhoneNumberTextView;

        }
    }


}

