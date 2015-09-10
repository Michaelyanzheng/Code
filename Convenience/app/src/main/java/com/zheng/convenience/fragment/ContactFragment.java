package com.zheng.convenience.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ListView;
import android.widget.TextView;

import com.zheng.convenience.R;

/**
 * Created by michael on 2015/9/8.
 */
public class ContactFragment extends ListFragment {

    private MyCursorAdapter mMyCursorAdapter;
    private LayoutAnimationController mLayoutAnimationController;
    private ScaleAnimation mScaleAnimation;

    @Override
    public void onResume() {

        mScaleAnimation = new ScaleAnimation(0,1,0,1);
        mScaleAnimation.setDuration(500);
        mLayoutAnimationController = new LayoutAnimationController(mScaleAnimation,0.5f);
        getListView().setLayoutAnimation(mLayoutAnimationController);

        super.onResume();
    }

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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        String phoneNumber = mMyCursorAdapter.getItem(position);

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+phoneNumber));
        startActivity(intent);

        super.onListItemClick(l, v, position, id);
    }

    private class MyCursorAdapter extends CursorAdapter {

        private int mViewResourceId = 0;
        private LayoutInflater mLayoutInflater = null;
        protected Cursor mCursor;

        public MyCursorAdapter(Context context, int viewResourceId, Cursor cursor) {
            super(context, cursor);
            mViewResourceId = viewResourceId;
            mCursor = cursor;
        }

        @Override
        public String getItem(int position) {

            mCursor.moveToPosition(position);

            String phoneNumber = mCursor.getString(
                    mCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));

            return phoneNumber;
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


    }

    private class ViewHolder {

        private TextView mPhoneNameTextView;

        private TextView mPhoneNumberTextView;

    }


}

