package com.lex.tang.cs.activity.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by tang on 2016/4/4.
 */
public abstract class BasePager {
    public Context mContext;

    public View mRootView;

    public BasePager(Context context) {
        mContext= context;
        mRootView = initView();
    }

    protected abstract View initView();

    public abstract void loadData();
}
