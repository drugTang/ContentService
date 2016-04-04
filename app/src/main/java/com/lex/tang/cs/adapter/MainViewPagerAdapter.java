package com.lex.tang.cs.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.lex.tang.cs.activity.pager.BasePager;
import com.lex.tang.cs.config.AppConfig;

import java.util.List;

/**
 * Created by tang on 2016/4/4.
 *
 */
public class MainViewPagerAdapter extends PagerAdapter {
    private List<BasePager> pages;
    public MainViewPagerAdapter(List<BasePager> pages) {
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = pages.get(position).mRootView;
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
