package com.zheng.getmyphonenumber;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michael on 2015/8/7.
 */
public class GetNumber {

    private static final String TAG = "GetNumber";

    public static List<PhoneInfo> mList = new ArrayList<PhoneInfo>();

    public static String getNumber(Context context){

        Cursor cursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);

        String phoneNumber;
        String phoneName;

        PhoneInfo phoneInfo = null;

        while(cursor.moveToNext()){
            phoneNumber = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            phoneName = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            phoneInfo = new PhoneInfo(phoneName,phoneNumber);
            mList.add(phoneInfo);

            Log.d(TAG,phoneName + "  ----- " + phoneNumber);
        }
        return null;
    }
}
