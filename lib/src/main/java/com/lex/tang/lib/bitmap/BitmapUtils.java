package com.lex.tang.lib.bitmap;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;

/**
 * Created by tang on 2016/4/2.
 */
public class BitmapUtils {

    /**
     * 加载图片
     *
     * @param imageView 需要显示图片的ImageView
     * @param url 图片地址
     */
    public static void displayImage(ImageView imageView, String url) {
        x.image().bind(imageView, url);
    }

    /**
     * 加载图片，并带有ImageOptions对象
     *
     * @param imageView 需要显示图片的ImageView
     * @param url 图片地址
     * @param options 自定义的图片属性
     */
    public static void displayImage(ImageView imageView, String url, ImageOptions options) {
        x.image().bind(imageView, url, options);
    }

    /**
     * 加载图片，并带有回调方法
     * @param imageView 需要显示图片的ImageView
     * @param url 图片地址
     * @param callback 回调方法
     */
    public static void displayImage(ImageView imageView, String url, Callback.CommonCallback<Drawable> callback) {
        x.image().bind(imageView,url,callback);
    }

    /**
     * 加载图片，并带有ImageOptions对象和回调方法
     * @param imageView 需要显示图片的ImageView
     * @param url 图片地址
     * @param options 自定义图片属性
     * @param callback 回调方法
     */
    public static void displayImage(ImageView imageView, String url, ImageOptions options, Callback.CommonCallback<Drawable> callback) {
        x.image().bind(imageView, url, options, callback);
    }

    /**
     * 下载图片，没有绑定ImageView
     * @param url 图片地址
     * @param options 自定义图片属性
     * @param callback 回调方法
     * @return 回调对象
     */
    public static Callback.Cancelable loadDrawable(String url, ImageOptions options, Callback.CommonCallback<Drawable> callback) {
        return x.image().loadDrawable(url, options, callback);
    }

    /**
     * 加载文件
     * @param url 文件地址
     * @param options 自定义图片属性
     * @param callback 回调方法
     * @return 回调对象
     */
    public static Callback.Cancelable loadFile(String url, ImageOptions options, Callback.CacheCallback<File> callback) {
        return x.image().loadFile(url, options, callback);
    }

    /**
     * 清除内存中的缓存
     */
    public static void clearMemCache() {
        x.image().clearMemCache();
    }

    /**
     * 清除文件缓存
     */
    public static void clearFilesCache() {
        x.image().clearCacheFiles();
    }

}
