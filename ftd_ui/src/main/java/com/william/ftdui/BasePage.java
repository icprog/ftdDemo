package com.william.ftdui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

public interface BasePage {

    @LayoutRes
    int setContentViewResId();


    void onCreated(@NonNull View view, @Nullable Bundle savedInstanceState);
}
