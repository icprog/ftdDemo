package com.william.ftdui.widget.aboutVP;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.william.ftdui.fragment.ReportFragment;

public class ReprotFragmentAdapter extends FragmentPagerAdapter {

    private ReportFragment[] fragments;

    public ReprotFragmentAdapter(FragmentManager fm, ReportFragment.ReprotFragmentParam... params) {
        super(fm);
        fragments = new ReportFragment[params.length];
        for (int i = 0; i < params.length; i++) {
            fragments[i] = ReportFragment.newInstance(params[i]);
        }
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "中医辩证";
            case 1:
            default:
                return "体质体质";
        }
    }
}
