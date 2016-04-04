package com.lex.tang.cs;

import android.content.Context;

import com.lex.tang.lib.BaseApplication;

/**
 * Created by tang on 2016/4/4.
 *
 */
public class AppBaseApplication extends BaseApplication{
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
