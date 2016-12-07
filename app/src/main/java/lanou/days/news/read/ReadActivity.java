package lanou.days.news.read;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lanou.days.R;
import lanou.days.base.BaseActivity;

/**
 * Created by 贾志强 on 16/11/30.
 */
public class ReadActivity extends BaseActivity{
    private WebView wv;
    @Override
    protected int getLayout() {
        return R.layout.activity_read;
    }

    @Override
    protected void initViews() {
        wv = bindView(R.id.wv_read);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl(intent.getStringExtra("read"));
    }
}
