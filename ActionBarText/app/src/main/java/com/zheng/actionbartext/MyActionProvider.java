package com.zheng.actionbartext;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/**
 * Created by michael on 2015/8/26.
 */
public class MyActionProvider extends android.view.ActionProvider {

    public MyActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        return null;
    }

    @Override
    public void onPrepareSubMenu(SubMenu subMenu) {

        subMenu.clear();

        subMenu.add(R.string.cart).
                setIcon(R.drawable.cart).
                setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

//                        showToast(R.string.cart);
                        return true;
                    }
                });

        subMenu.add(R.string.house).
                setIcon(R.drawable.house).
                setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        //                        showToast(R.string.house);


                        return false;
                    }
                });
    }

    @Override
    public boolean hasSubMenu() {
        return true;
    }

}
