package lanou.days.news.technology;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by 贾志强 on 16/11/30.
 */
public class TechnologyActivity extends BaseActivity{
    private WebView webView;
    @Override
    protected int getLayout() {
        return R.layout.activity_technology;
    }
    @Override
    protected void initViews() {
        webView = bindView(R.id.wv_technology);
    }
    @Override
    protected void initData() {
        Intent intent = getIntent();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(intent.getStringExtra("technology"));
    }
}
