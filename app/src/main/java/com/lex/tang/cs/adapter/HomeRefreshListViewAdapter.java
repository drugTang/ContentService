package com.lex.tang.cs.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lex.tang.cs.AppBaseApplication;
import com.lex.tang.cs.R;
import com.lex.tang.cs.entity.News.ShowapiResBodyBean.PagebeanBean;
import com.lex.tang.cs.utils.LogUtils;
import com.lex.tang.lib.bitmap.BitmapUtils;

import java.util.List;

/**
 * Created by tang on 2016/4/4.
 *
 */
public class HomeRefreshListViewAdapter extends BaseAdapter {
    private List<PagebeanBean.ContentlistBean> mContentList;

    private final int TYPE_ONE = 0, TYPE_TWO = 1, TYPE_COUNT = 2;

    public HomeRefreshListViewAdapter(List<PagebeanBean.ContentlistBean> data) {
        mContentList = data;
    }

    @Override
    public int getCount() {
        return mContentList.size();
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return mContentList.get(position).getImageurls().size() == 0 ? TYPE_ONE : TYPE_TWO;
    }

    @Override
    public Object getItem(int position) {
        return mContentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        int type = getItemViewType(position);
        if(convertView == null) {
            if(type == TYPE_ONE) {
                convertView = View.inflate(AppBaseApplication.getContext(),R.layout.list_view_news_item_b,null);
                viewHolder = new ViewHolder();
                viewHolder.source = (TextView) convertView.findViewById(R.id.tv_source);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_news_title);
                viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            } else {
                convertView = View.inflate(AppBaseApplication.getContext(),R.layout.list_view_news_item_a,null);
                viewHolder = new ViewHolder();
                viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_news);
                viewHolder.source = (TextView) convertView.findViewById(R.id.tv_source);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tv_news_title);
                viewHolder.time = (TextView) convertView.findViewById(R.id.tv_time);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PagebeanBean.ContentlistBean temp = mContentList.get(position);
        if(type == TYPE_ONE) {
            viewHolder.title.setText(temp.getTitle());
            viewHolder.time.setText(temp.getPubDate());
            viewHolder.source.setText(temp.getSource());
        } else {
            BitmapUtils.displayImage(viewHolder.img,temp.getImageurls().get(0).getUrl());
            viewHolder.title.setText(temp.getTitle());
            viewHolder.time.setText(temp.getPubDate());
            viewHolder.source.setText(temp.getSource());
        }
        return convertView;
    }

    class ViewHolder {
        ImageView img;
        TextView title;
        TextView time;
        TextView source;
    }
}
