package com.pku.pkuapp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;
import com.pku.pkuapp.base.MyLog;
import com.pku.pkuapp.utils.NetworkUtil;

/**
 * Created by admin on 15/12/27.
 */
public class WebViewActivity extends BaseActivity {

    private static final String yuejing_aboutmeright = "http://bbs.evolvingera.com.cn/index.php/api/evolvingera/aboutus/1";

    private WebView wv;
    private TextView titleView;
    private String url = yuejing_aboutmeright;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        titleView = (TextView) findViewById(R.id.tv_title_text);
        TextView leftBtn = (TextView) findViewById(R.id.iv_left_btn);
        leftBtn.setText("<");
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        wv = (WebView) findViewById(R.id.webView);
        pb = (ProgressBar) findViewById(R.id.progress);

        String title = getIntent().getStringExtra("title");
        titleView.setText(title);
        // url = getIntent().getStringExtra("remote_url");
        MyLog.i("remote_url" + url);
        initWebView(url);

        if (!NetworkUtil.isNetworkAvailable(this)) {
            //UIUtils.showCustomDialog(this,"请连接网络！");
            Toast.makeText(getApplicationContext(), "请连接网络！", Toast.LENGTH_LONG).show();
        }
    }

    private void initWebView(String url) {

        wv.getSettings().setJavaScriptEnabled(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        WebSettings webSettings = wv.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        if (TextUtils.isEmpty(url)) {
            wv.loadUrl(yuejing_aboutmeright);
        } else
            wv.loadUrl(url);
        //加载数据
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //titleView.setText("加载完成");
                    pb.setVisibility(View.GONE);
                } else {
                    pb.setProgress(newProgress);
                    //titleView.setText("加载中.......");

                }
            }
        });
        //这个是当网页上的连接被点击的时候
        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    // goBack()表示返回webView的上一页面
    public boolean onKeyDown(int keyCoder, KeyEvent event) {
        if (wv.canGoBack() && keyCoder == KeyEvent.KEYCODE_BACK) {
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCoder, event);
    }

}
