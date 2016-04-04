package com.lex.tang.cs.activity.pager.main;

import android.content.Context;
import android.view.View;

import com.lex.tang.cs.R;
import com.lex.tang.cs.activity.pager.BasePager;
import com.lex.tang.cs.config.AppConfig;
import com.lex.tang.cs.net.CommonCallback;
import com.lex.tang.cs.utils.LogUtils;
import com.lex.tang.lib.net.HttpUtils;

import org.xutils.http.RequestParams;

/**
 * Created by tang on 2016/4/4.
 */
public class HomePager extends BasePager {

    public HomePager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.pager_home_view,null);
        return view;
    }

    @Override
    public void loadData() {
        RequestParams params = new RequestParams(AppConfig.NEWS_API);
        HttpUtils.get(params, new CommonCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.d(result);

            }
        });
    }
}
