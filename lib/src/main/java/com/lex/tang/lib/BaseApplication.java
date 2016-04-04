package com.lex.tang.lib;

import android.app.Application;

import org.xutils.x;

/**
 * Created by tang on 2016/4/2.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化xUtils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
