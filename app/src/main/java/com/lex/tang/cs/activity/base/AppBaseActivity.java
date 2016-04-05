package com.lex.tang.cs.activity.base;

import android.content.Intent;

import com.lex.tang.lib.activity.BaseActivity;

/**
 * Created by tang on 2016/4/4.
 */
public abstract class AppBaseActivity extends BaseActivity {


    public void openActivity(Class cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}
