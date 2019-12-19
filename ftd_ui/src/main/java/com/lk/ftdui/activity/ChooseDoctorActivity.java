package com.lk.ftdui.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lk.ftdui.R;

public class ChooseDoctorActivity extends BaseActivity {

    public static void start(Activity activity){

    }

    private RecyclerView rv;

    @Override
    public int setContentViewResId() {
        return R.layout.activity_choose_doctor;
    }

    @Override
    public void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
}
