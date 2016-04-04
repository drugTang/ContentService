package com.lex.tang.cs.utils;

import android.util.Log;

import com.lex.tang.cs.config.AppConfig;

/**
 * Created by tang on 2016/4/4.
 */
public class LogUtils {
    public static final int LEVEL_NONE = 0;
    public static final int LEVEL_VERBOSE = 1;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_WARN = 4;
    public static final int LEVEL_ERROR = 5;

    public static final String mTag = AppConfig.APP_NAME;

    /**
     * 最低打印日志的级别
     */
    public static int debugable = LEVEL_VERBOSE;

    public static void v(String msg) {
        if(debugable <= LEVEL_VERBOSE) {
            Log.v(mTag, msg);
        }
    }
    public static void d(String msg) {
        if(debugable <= LEVEL_DEBUG) {
            Log.d(mTag, msg);
        }
    }
    public static void i(String msg) {
        if(debugable <= LEVEL_INFO) {
            Log.i(mTag, msg);
        }
    }
    public static void w(String msg) {
        if(debugable <= LEVEL_WARN) {
            Log.w(mTag, msg);
        }
    }
    public static void w(Throwable tr) {
        if(debugable <= LEVEL_WARN) {
            Log.w(mTag, tr);
        }
    }
    public static void w(String msg, Throwable tr) {
        if(debugable <= LEVEL_WARN && msg != null) {
            Log.w(mTag, msg, tr);
        }
    }
    public static void e(String msg) {
        if(debugable <= LEVEL_ERROR) {
            Log.e(mTag, msg);
        }
    }

    public static void e(String msg,Throwable tr) {
        if(debugable <= LEVEL_ERROR && msg != null) {
            Log.e(mTag, msg, tr);
        }
    }
}
