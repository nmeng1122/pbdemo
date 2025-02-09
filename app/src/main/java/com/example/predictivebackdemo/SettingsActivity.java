package com.example.predictivebackdemo;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.window.OnBackInvokedCallback;

public class SettingsActivity extends PreferenceActivity {

    private final OnBackInvokedCallback mOnBackInvokedCallback = this::getOnBackInvokedDispatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onBackPressed() {
        if (!WindowOnBackInvokedDispatcher.isOnBackInvokedCallbackEnable(this)) return;
        // 检查当前是否在子 PreferenceScreen 中
        if (isSubScreenOpen()) {
            // 如果是子页面，则返回上一级
            goBackToParentScreen();
        } else {
            // 如果已经是顶级页面，则关闭 Activity
            super.onBackPressed();
        }
    }

    /**
     * 检查当前是否在子 PreferenceScreen 中
     */
    private boolean isSubScreenOpen() {
        return getPreferenceScreen().getDialog() != null;
    }

    /**
     * 返回上一级 PreferenceScreen
     */
    private void goBackToParentScreen() {
        // 关闭当前子 PreferenceScreen
        getPreferenceScreen().getDialog().dismiss();
    }
}