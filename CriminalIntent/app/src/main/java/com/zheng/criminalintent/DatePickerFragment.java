package com.zheng.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by michael on 2015/8/26.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.zheng.criminalintent.date";

    private Date mDate;

    public static DatePickerFragment newInstance(Date date){

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_DATE, date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);

        return datePickerFragment;
    }

    private void sendResult(int resultCode){

        if (getTargetFragment() == null){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,mDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mDate = (Date) getArguments().getSerializable(EXTRA_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

        DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                mDate = new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();

                getArguments().putSerializable(EXTRA_DATE,mDate);
            }
        });

        return new AlertDialog.Builder(getActivity()).
                setView(view).
                setTitle(R.string.date_picker_title).
                setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        }).
                create();
    }
}
