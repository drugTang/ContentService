package com.lex.tang.cs.view;

/**
 * Created by tang on 2016/4/4.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lex.tang.cs.R;

public class RefreshListView extends ListView implements OnScrollListener {
    public static final int STATUS_PULL_TO_REFRESH = 0;
    public static final int STATUS_RELEASE_TO_REFRESH = 1;
    public static final int STATUS_REFRESHING = 2;

    private int mCurrentStatus = STATUS_PULL_TO_REFRESH;

    private float startY = -1;
    private int mHeaderHeight;
    private View mHeaderView;
    private ImageView mArrowDown;
    private ImageView mArrowUp;
    private ProgressBar mProgressBar;
    private TextView mDescription;
    private TextView mUpdatedTime;
    private OnRefreshListener mListener;
    private int mFooterHeight;
    private boolean isLoadingMore;
    private View mFooterView;

    public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initHeaderView();
        initFooterView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
        initFooterView();
    }

    public RefreshListView(Context context) {
        super(context);
        initHeaderView();
        initFooterView();
    }

    private void initHeaderView() {
        mHeaderView = View.inflate(getContext(), R.layout.refresh_list_view_header_view, null);
        mArrowDown = (ImageView) mHeaderView.findViewById(R.id.iv_arrow_down);
        mArrowUp = (ImageView) mHeaderView.findViewById(R.id.iv_arrow_up);
        mProgressBar = (ProgressBar) mHeaderView.findViewById(R.id.progress_bar);
        mDescription = (TextView) mHeaderView.findViewById(R.id.description);
        mUpdatedTime = (TextView) mHeaderView.findViewById(R.id.updated_time);

        mHeaderView.measure(0, 0);
        mHeaderHeight = mHeaderView.getMeasuredHeight();
        mHeaderView.setPadding(0, -mHeaderHeight, 0, 0);

        this.addHeaderView(mHeaderView);
    }

    private void initFooterView() {
        mFooterView = View.inflate(getContext(), R.layout.refresh_list_view_footer_view, null);
        mFooterView.measure(0, 0);
        mFooterHeight = mFooterView.getMeasuredHeight();
        mFooterView.setPadding(0, -mFooterHeight, 0, 0);
        this.addFooterView(mFooterView);
        this.setOnScrollListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mCurrentStatus == STATUS_REFRESHING) {
                    break;
                }
                if (startY == -1) {
                    startY = event.getRawY();
                }
                float endY = event.getRawY();
                int distanceY = (int) (endY - startY + 0.5f);// Y轴方向偏移量
                // 如果滑动距离大于0且当前显示的为第一个
                if (distanceY > 0 && getFirstVisiblePosition() == 0) {
                    int padding = distanceY - mHeaderHeight;
                    mHeaderView.setPadding(0, padding, 0, 0);
                    if (padding < 0 && mCurrentStatus != STATUS_PULL_TO_REFRESH) {
                        mCurrentStatus = STATUS_PULL_TO_REFRESH;
                        refreshStatus();
                    } else if (padding >= 0 && mCurrentStatus != STATUS_RELEASE_TO_REFRESH) {
                        mCurrentStatus = STATUS_RELEASE_TO_REFRESH;
                        refreshStatus();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
                // 如果当前状态为0则隐藏头布局，如果当前状态为1则修改状态为正在刷新
                startY = -1;
                if (mCurrentStatus == STATUS_RELEASE_TO_REFRESH) {
                    mCurrentStatus = STATUS_REFRESHING;
                    mHeaderView.setPadding(0, 0, 0, 0);
                    refreshStatus();
                } else if (mCurrentStatus == STATUS_PULL_TO_REFRESH) {
                    mHeaderView.setPadding(0, -mHeaderHeight, 0, 0);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void refreshStatus() {
        switch (mCurrentStatus) {
            case STATUS_PULL_TO_REFRESH:
                mArrowDown.setVisibility(View.VISIBLE);
                mArrowUp.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
                mDescription.setText("下拉刷新");
                break;
            case STATUS_RELEASE_TO_REFRESH:
                mArrowDown.setVisibility(View.GONE);
                mArrowUp.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);

                mDescription.setText("松开刷新");
                break;
            case STATUS_REFRESHING:
                mArrowDown.setVisibility(View.GONE);
                mArrowUp.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                mDescription.setText("正在刷新");
                if (mListener != null) {
                    mListener.onRefresh();
                }
                break;
        }
    }

    public void refreshComplete(boolean isSuccess) {
        mCurrentStatus = STATUS_PULL_TO_REFRESH;
        mDescription.setText("下拉刷新");
        mArrowDown.setVisibility(View.VISIBLE);
        mArrowUp.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mHeaderView.setPadding(0, -mHeaderHeight, 0, 0);
        if (isSuccess) {
            mUpdatedTime.setText("上次刷新时间：" + getCurrentTime());
        }
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }

    public interface OnRefreshListener {
        void onRefresh();
        void onLoadingMore();
    }

    public String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(new Date());
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState == SCROLL_STATE_FLING || scrollState == SCROLL_STATE_IDLE) {
			/*
			 * 1.判断当前的ListView是不是处于最后一个，并且当前不是正在加载
			 * 2.显示脚布局
			 * 3.改变ListView的显示位置
			 * 4.调用监听器方法
			 */
            if(getLastVisiblePosition() == getCount() - 1 && !isLoadingMore) {
                mFooterView.setPadding(0, 0, 0, 0);
                this.setSelection(getCount() - 1);
                isLoadingMore = true;
                if(mListener != null) {
                    mListener.onLoadingMore();
                }
            }
        }
    }

    /**
     * 加载更多完成后调用
     */
    public void loadMoreComplete() {
        isLoadingMore = false;
        mFooterView.setPadding(0, -mFooterHeight, 0, 0);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

}
