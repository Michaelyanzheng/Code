package com.zheng.sharedpreferences;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class MainActivity extends ActionBarActivity {

    private CheckBox checkBox;

    private SharedPreferences sharedPreferences;

    private final static String SHOW_ALERDIALOG = "show_alertDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox) findViewById(R.id.checkbox);

        sharedPreferences = getSharedPreferences(SHOW_ALERDIALOG, Context.MODE_PRIVATE);
        checkBox.setChecked(sharedPreferences.getBoolean("mycb",false));


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("mycb",isChecked);
                editor.commit();
            }
        });

        if (checkBox.isChecked()){
            new AlertDialog.Builder(this).setTitle("欢迎").setMessage("你好，欢迎使用").setPositiveButton("zheng 关闭",null).show();
        }


    }
}
