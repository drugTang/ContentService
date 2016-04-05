package com.lex.tang.cs.activity.pager.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;

import com.lex.tang.cs.R;
import com.lex.tang.cs.activity.pager.BasePager;
import com.lex.tang.cs.adapter.HomeRefreshListViewAdapter;
import com.lex.tang.cs.adapter.HomeViewPagerAdapter;
import com.lex.tang.cs.config.AppConfig;
import com.lex.tang.cs.entity.News;
import com.lex.tang.cs.net.CommonCallback;
import com.lex.tang.cs.utils.LogUtils;
import com.lex.tang.cs.view.RefreshListView;
import com.lex.tang.cs.view.indicator.TabPageIndicator;
import com.lex.tang.lib.net.HttpUtils;
import com.lex.tang.lib.utils.JsonParse;

import org.xutils.http.RequestParams;

import java.util.List;

/**
 * Created by tang on 2016/4/4.
 */
public class HomePager extends BasePager {
    private ViewPager mViewPager;
    private TabPageIndicator mIndicator;

    public HomePager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.pager_home_view,null);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_home);
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        mViewPager.setAdapter(new HomeViewPagerAdapter(mContext));
        mIndicator.setViewPager(mViewPager);
        return view;
    }

    @Override
    public void loadData() {


//
    }
}
