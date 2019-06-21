package com.william.ftdui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainVPAdapter extends FragmentPagerAdapter {

    public MainVPAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return CameraFragment.newInstance(R.drawable.xuxian_mian,FtdActivity.FACE);
            case 1:
                return CameraFragment.newInstance(R.drawable.xuxian_she,FtdActivity.TONGUE_TOP);
            case 2:
                return CameraFragment.newInstance(R.drawable.xuxian_shedi,FtdActivity.TONGUE_BOTTOM);
            case 3:
//                return CameraFragment.newInstance(R.drawable.xuxian_mian,FtdActivity.TONGUE_BOTTOM);todo 问诊
                default:
                    return BlankFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
