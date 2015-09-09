package com.zheng.layoutaniimationlistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;


public class MainActivity extends ListActivity {

    private ArrayAdapter<String> mArrayAdapter;
    private LayoutAnimationController mLayoutAnimationController;
    private ScaleAnimation mScaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                new String[]{"zheng","michael","shouli"});

        setListAdapter(mArrayAdapter);

//        mScaleAnimation = new ScaleAnimation(0,1,0,1);
//        mScaleAnimation.setDuration(1000);
//
//        mLayoutAnimationController = new LayoutAnimationController(mScaleAnimation,0.5f);
//
//        getListView().setLayoutAnimation(mLayoutAnimationController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
