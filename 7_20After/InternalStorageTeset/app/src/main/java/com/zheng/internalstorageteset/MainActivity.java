package com.zheng.internalstorageteset;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {


    private EditText etSave;

    private Button btnSave;

    private static final String FILE_NAME = "internalstorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSave = (EditText) findViewById(R.id.etSave);
        btnSave = (Button) findViewById(R.id.btnSave);

        getContent();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveContent();

            }
        });

    }

    private void getContent(){

        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            byte [] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            etSave.setText(new String(bytes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveContent(){

        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(etSave.getText().toString().getBytes("UTF-8"));

            fileOutputStream.flush();
            fileOutputStream.close();

            Toast.makeText(MainActivity.this,"save Success",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}













