package com.zheng.getmyphonenumber;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView lv;
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetNumber.getNumber(this);

        lv = (ListView) findViewById(R.id.lv);

        mMyAdapter = new MyAdapter(GetNumber.mList,this);
        lv.setAdapter(mMyAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String phoneNumber = mMyAdapter.getItem(position).getPhoneNumber();

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+phoneNumber));

                startActivity(intent);

            }
        });
    }

}
