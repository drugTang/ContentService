package com.lex.tang.lib.net;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by tang on 2016/4/2.
 */
public class HttpUtils {

    /**
     * 普通的get请求方式
     * @param params
     * @param callback
     */
    public static Callback.Cancelable get(RequestParams params, Callback.CommonCallback<?> callback) {
        return x.http().get(params, callback);
    }

    /**
     * 带有请求的get请求方式
     * @param params
     * @param callback
     */
    public static Callback.Cancelable getWithCache(RequestParams params, Callback.CacheCallback<?> callback) {
        return x.http().get(params, callback);
    }

    /**
     * 同步的get请求方式
     * @param params
     * @param resultType
     * @throws Throwable
     */
    public static <T> T getSync(RequestParams params, Class<T> resultType) throws Throwable {
        return x.http().getSync(params, resultType);
    }

    /**
     * 普通的post请求方式
     * @param params
     * @param callback
     */
    public static Callback.Cancelable post(RequestParams params, Callback.CommonCallback<?> callback) {
        return x.http().post(params, callback);
    }

    /**
     * 带有缓存的post请求方式
     * @param params
     * @param callback
     */
    public static Callback.Cancelable postWithCache(RequestParams params, Callback.CacheCallback callback) {
        return x.http().post(params,callback);
    }

    /**
     * 同步的post请求方式
     * @param params
     * @param resultType
     * @throws Throwable
     */
    public static <T> T postSync(RequestParams params, Class<T> resultType) throws Throwable {
        return x.http().postSync(params,resultType);
    }


}
