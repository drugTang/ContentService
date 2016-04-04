package com.lex.tang.cs.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lex.tang.cs.AppBaseApplication;
import com.lex.tang.cs.R;
import com.lex.tang.cs.config.AppConfig;
import com.lex.tang.cs.entity.News;
import com.lex.tang.cs.net.CommonCallback;
import com.lex.tang.lib.net.HttpUtils;
import com.lex.tang.lib.utils.JsonParse;

import org.xutils.http.RequestParams;

import java.util.List;

/**
 * Created by tang on 2016/4/4.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private String[] tabs = AppConfig.NEWS_TABS;
    private ListView mRefreshListView;


    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(AppBaseApplication.getContext(), R.layout.view_pager_home_view,null);
        container.addView(view);
        mRefreshListView = (ListView) view.findViewById(R.id.rlv_home);
        RequestParams params = new RequestParams(AppConfig.NEWS_API);
        params.addBodyParameter("channelName",tabs[position]);
        HttpUtils.get(params, new CommonCallback() {
            @Override
            public void onSuccess(String result) {
                News news = JsonParse.fromJson(result, News.class);
                if (news.getShowapi_res_code() == 0) {
                    List<News.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentList = news.getShowapi_res_body().getPagebean().getContentlist();
                    mRefreshListView.setAdapter(new HomeRefreshListViewAdapter(contentList));
                }
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
