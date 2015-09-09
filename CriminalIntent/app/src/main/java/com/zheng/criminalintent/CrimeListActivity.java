package com.zheng.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by michael on 2015/8/24.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return new CrimeListFragment();
    }
}
