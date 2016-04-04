package com.lex.tang.cs.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.lex.tang.cs.R;
import com.lex.tang.cs.activity.base.AppBaseActivity;
import com.lex.tang.cs.activity.pager.BasePager;
import com.lex.tang.cs.activity.pager.main.BBSPager;
import com.lex.tang.cs.activity.pager.main.HomePager;
import com.lex.tang.cs.activity.pager.main.LibPager;
import com.lex.tang.cs.activity.pager.main.PersonPager;
import com.lex.tang.cs.activity.pager.main.StorePager;
import com.lex.tang.cs.adapter.MainViewPagerAdapter;
import com.lex.tang.cs.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppBaseActivity {
    private Context mContext;
    private RadioGroup mRadioGroup;
    private NoScrollViewPager mViewPager;
    private List<BasePager> mPagers;

    @Override
    protected void initVariables() {
        mContext = this;
        mPagers = new ArrayList<>();
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        setContentView(R.layout.activity_main);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_tab);
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_main);
        mPagers.add(new HomePager(this));
        mPagers.add(new LibPager(this));
        mPagers.add(new BBSPager(this));
        mPagers.add(new StorePager(this));
        mPagers.add(new PersonPager(this));
        mViewPager.setAdapter(new MainViewPagerAdapter(mPagers));
    }

    @Override
    protected void loadData() {
        mPagers.get(0).loadData();
        mRadioGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.rb_home) {
                mPagers.get(0).loadData();
                mViewPager.setCurrentItem(0);
            } else if(checkedId == R.id.rb_lib) {
                mPagers.get(1).loadData();
                mViewPager.setCurrentItem(1);
            }else if(checkedId == R.id.rb_bbs) {
                mPagers.get(2).loadData();
                mViewPager.setCurrentItem(2);
            }else if(checkedId == R.id.rb_store) {
                mPagers.get(3).loadData();
                mViewPager.setCurrentItem(3);
            }else if(checkedId == R.id.rb_person) {
                mPagers.get(4).loadData();
                mViewPager.setCurrentItem(4);
            }
        }
    };
}
