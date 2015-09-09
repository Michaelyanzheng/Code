package com.zheng.actionbartext;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import com.zheng.actionbartext.fragment.AlbumFragment;
import com.zheng.actionbartext.fragment.ArtistFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //判断手机有没有物理Menu键的
        setOverflowShowingAlways();

        ActionBar actionBar = getActionBar();
        //在ActionBar图标的左侧出现了一个向左的箭头 android.R.id.home
        actionBar.setDisplayHomeAsUpEnabled(true);


        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab().
                setText(R.string.artist).
                setTabListener(
                        new TabListener<ArtistFragment>(
                                this,getString(R.string.artist),ArtistFragment.class));

        actionBar.addTab(tab);

        tab = actionBar.newTab().
                setText(R.string.album).
                setTabListener(
                        (ActionBar.TabListener) new TabListener<AlbumFragment>(
                                this, getString(R.string.album), AlbumFragment.class));

        actionBar.addTab(tab);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                showToast(R.string.action_expand);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {

                showToast(R.string.action_collapse);
                return true;
            }
        });

        //会将所有可以共享图片的程序都列出来
//        MenuItem shareItem = menu.findItem(R.id.action_share);
//        ShareActionProvider shareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
//        shareActionProvider.setShareIntent(getDefaultIntent());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.action_compose:

                showToast(R.string.action_compose);
                break;

            case R.id.action_delete:

                showToast(R.string.action_delete);

                break;

            case R.id.action_settings:

                showToast(R.string.action_settings);

                break;

            case android.R.id.home:

                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this)
                            .addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private Intent getDefaultIntent(){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType(Config.IMAGE_INTENT);
        return intent;
    }

    /**
     * Show Toast
     * @param stringId
     */
    private void showToast(int stringId){
        Toast.makeText(this, stringId,Toast.LENGTH_SHORT).show();
    }


    /**
     * Action按钮应不应该显示图标，
     * 是由MenuBuilder这个类的setOptionalIconsVisible方法
     *
     * @param featureId
     * @param menu
     * @return
     */

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        if (featureId == Window.FEATURE_ACTION_BAR && menu != null){

            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {

                try {

                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);

                } catch (Exception e) {

                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 判断手机有没有物理Menu键的
     */
    private void setOverflowShowingAlways() {

        try {

            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");

            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
