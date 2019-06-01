package dmit2504.nait.sharedpreferencedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_view);

        mWebView = findViewById(R.id.activity_website_view_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
//        mWebView.loadUrl("https://moodle.nait.ca");
        // Retrieve the url data passed into this activity
        String websiteUrl = getIntent().getStringExtra(WebsiteListActivity.EXTRA_URL);
        mWebView.loadUrl(websiteUrl);

    }
}
