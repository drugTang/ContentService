package com.lex.tang.cs.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lex.tang.cs.AppBaseApplication;
import com.lex.tang.cs.R;
import com.lex.tang.cs.activity.NewsDetailActivity;
import com.lex.tang.cs.config.AppConfig;
import com.lex.tang.cs.entity.News;
import com.lex.tang.cs.net.CommonCallback;
import com.lex.tang.cs.utils.LogUtils;
import com.lex.tang.cs.view.RefreshListView;
import com.lex.tang.cs.view.SwipeRefreshView.SwipeRefreshLayout;
import com.lex.tang.lib.net.HttpUtils;
import com.lex.tang.lib.utils.JsonParse;

import org.xutils.http.RequestParams;

import java.util.List;

/**
 * Created by tang on 2016/4/4.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private String[] tabs = AppConfig.NEWS_TABS;
    private List<News.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentList;
    private HomeRefreshListViewAdapter adapter ;
    private int currentPage = 1;
    private Context context;
    public HomeViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(AppBaseApplication.getContext(), R.layout.view_pager_home_view,null);
        container.addView(view);
        final ListView mRefreshListView = (ListView) view.findViewById(R.id.rlv_home);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_layout);
        final RelativeLayout emptyView = (RelativeLayout) view.findViewById(R.id.rl_empty);
        RequestParams params = new RequestParams(AppConfig.NEWS_API);
        params.addBodyParameter("channelName", tabs[position]);
        HttpUtils.get(params, new CommonCallback() {
            @Override
            public void onSuccess(String result) {
                News news = JsonParse.fromJson(result, News.class);
                if (news.getShowapi_res_code() == 0) {
                    contentList = news.getShowapi_res_body().getPagebean().getContentlist();
                    adapter = new HomeRefreshListViewAdapter(contentList);
                    mRefreshListView.setAdapter(adapter);
                    mRefreshListView.setEmptyView(emptyView);

                }
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        RequestParams params = new RequestParams(AppConfig.NEWS_API);
                        params.addBodyParameter("channelName", tabs[position]);
                        HttpUtils.get(params, new CommonCallback() {
                            @Override
                            public void onSuccess(String result) {
                                News news = JsonParse.fromJson(result, News.class);
                                List<News.ShowapiResBodyBean.PagebeanBean.ContentlistBean> beans = news.getShowapi_res_body().getPagebean().getContentlist();
                                if (news.getShowapi_res_code() == 0) {
                                    for (int i = 0; i < contentList.size(); i++) {
                                        if (!contentList.get(i).getLink().equals(beans.get(i).getLink())) {
                                            contentList.add(beans.get(i));
                                            LogUtils.d("contain");
                                        }
                                    }
                                    adapter.notifyDataSetChanged();
                                    refreshLayout.setRefreshing(false);
                                }
                            }
                        });

                    }
                });

            }
        });
        refreshLayout.setOnLoadListener(new SwipeRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        RequestParams params = new RequestParams(AppConfig.NEWS_API);
                        params.addBodyParameter("channelName", tabs[position]);
                        if (currentPage <= 20) {
                            params.addBodyParameter("page", ++currentPage + "");
                            HttpUtils.get(params, new CommonCallback() {
                                @Override
                                public void onSuccess(String result) {
                                    News news = JsonParse.fromJson(result, News.class);
                                    if (news.getShowapi_res_code() == 0) {
                                        contentList.addAll(news.getShowapi_res_body().getPagebean().getContentlist());
                                        adapter.notifyDataSetChanged();
                                        refreshLayout.setLoading(false);
                                        Toast.makeText(AppBaseApplication.getContext(), "加载成功", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(AppBaseApplication.getContext(), "没有更多了!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        mRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context,NewsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",contentList.get(position).getLink());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        refreshLayout.setColor(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        refreshLayout.setMode(SwipeRefreshLayout.Mode.BOTH);
        refreshLayout.setLoadNoFull(false);
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
