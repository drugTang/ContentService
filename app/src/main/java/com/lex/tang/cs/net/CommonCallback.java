package com.lex.tang.cs.net;

import com.lex.tang.cs.utils.LogUtils;

import org.xutils.common.Callback;

/**
 * Created by tang on 2016/4/4.
 */
public class CommonCallback implements Callback.CommonCallback<String>{
    @Override
    public void onSuccess(String result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        LogUtils.d("onError:"+ex.getMessage());
    }

    @Override
    public void onCancelled(CancelledException cex) {
        LogUtils.d("onCancelled:"+cex.getMessage());
    }

    @Override
    public void onFinished() {
        LogUtils.d("onFinished...");
    }
}
