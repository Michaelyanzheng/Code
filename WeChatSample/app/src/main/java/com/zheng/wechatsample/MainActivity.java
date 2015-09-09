package com.zheng.wechatsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends FragmentActivity {

    private ChatFragment mChatFragment;
    private FoundFragment mFoundFragment;
    private ContactsFragment mContactsFragment;

    private com.zheng.wechatsample.PagerSlidingTabStrip mPagerSlidingTabStrip;
    private DisplayMetrics mDisplayMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //   永远显示overflow按钮
        setOverflowShowingAlways();

        mDisplayMetrics = getResources().getDisplayMetrics();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.pagerSlidingTabStrip);

        viewPager.setAdapter(new MyViewPagerAdpter(getSupportFragmentManager()));

        mPagerSlidingTabStrip.setViewPager(viewPager);

        setTabsValue();
    }

    private void setTabsValue(){

        // 设置Tab是自动填充满屏幕的
        mPagerSlidingTabStrip.setShouldExpand(true);

        // 设置Tab的分割线是透明的
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);

        // 设置Tab底部线的高度
        mPagerSlidingTabStrip.setUnderlineHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, mDisplayMetrics));

        // 设置Tab Indicator的高度
        mPagerSlidingTabStrip.setIndicatorHeight(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, mDisplayMetrics)
        );

        // 设置Tab标题文字的大小
        mPagerSlidingTabStrip.setTextSize(
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, mDisplayMetrics)
        );

        // 设置Tab Indicator的颜色
        mPagerSlidingTabStrip.setIndicatorColor(Color.parseColor("#45c01a"));

        // 设置选中Tab文字的颜色
        mPagerSlidingTabStrip.setTextColor(Color.parseColor("#45c01a"));

        // 取消点击Tab时的背景色
        mPagerSlidingTabStrip.setTabBackground(0);
    }

    public class MyViewPagerAdpter extends FragmentPagerAdapter{

        public MyViewPagerAdpter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        private final String[] titles = {"聊天","发现","通讯录"};

        public CharSequence getPageTitle(int position){
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){

                case 0:

                    if (mChatFragment == null){
                        mChatFragment = new ChatFragment();
                    }
                    return mChatFragment;

                case 1:

                    if (mFoundFragment == null){
                        mFoundFragment = new FoundFragment();
                    }
                    return mFoundFragment;

                case 2:

                    if (mContactsFragment == null){
                        mContactsFragment = new ContactsFragment();
                    }
                    return mContactsFragment;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

    /**
     * 加载menu
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    /**
     * menuItem 被点击调用
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_search:

                showToast(R.string.action_search);

            break;

            case R.id.action_plus:

                showToast(R.string.action_plus);

            break;

            case R.id.action_album:

                showToast(R.string.action_album);

            break;

            case R.id.action_collection:

                showToast(R.string.action_collection);

            break;

            case R.id.action_card:

                showToast(R.string.action_card);

            break;

            case R.id.action_settings:

                showToast(R.string.action_settings);

            break;

            case R.id.action_feed:

                showToast(R.string.action_feed);

            break;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 让Overflow中的选项显示图标
     * @param featureId
     * @param menu
     * @return
     */

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {

        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {

            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {

                try {

                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);

                } catch (Exception e) {

                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 永远显示overflow按钮
     */

    private void setOverflowShowingAlways() {

        try {

            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");

            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Toast message
     * @param stringId
     */

    private void showToast(int stringId){

        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

}