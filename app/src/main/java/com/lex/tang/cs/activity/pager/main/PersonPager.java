package com.lex.tang.cs.activity.pager.main;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lex.tang.cs.activity.pager.BasePager;

/**
 * Created by tang on 2016/4/4.
 */
public class PersonPager extends BasePager {
    public PersonPager(Context context) {
        super(context);
    }

    @Override
    protected View initView() {
        TextView text = new TextView(mContext);
        text.setText("123");
        return text;
    }

    @Override
    public void loadData() {

    }
}
