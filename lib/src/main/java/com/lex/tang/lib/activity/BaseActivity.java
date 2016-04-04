package com.lex.tang.lib.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tang on 2016/4/2.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initView(savedInstanceState);
        loadData();
    }

    protected abstract void initVariables();

    protected abstract void initView(Bundle saveInstanceState);

    protected abstract void loadData();
}
