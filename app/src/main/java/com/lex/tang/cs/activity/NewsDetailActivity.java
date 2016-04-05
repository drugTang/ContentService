package com.lex.tang.cs.activity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lex.tang.cs.R;
import com.lex.tang.cs.activity.base.AppBaseActivity;

/**
 * Created by WEIZONE on 2016/4/5.
 *
 */
public class NewsDetailActivity extends AppBaseActivity {
    private String url;
    private WebView webView;

    @Override
    protected void initVariables() {
        url = getIntent().getExtras().getString("url");
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        setContentView(R.layout.activity_news_detail);
        webView = (WebView) findViewById(R.id.web_view);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    protected void loadData() {
        webView.loadUrl(url);
    }
}
