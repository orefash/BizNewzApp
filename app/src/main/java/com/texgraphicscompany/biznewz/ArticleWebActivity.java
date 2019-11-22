package com.texgraphicscompany.biznewz;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ArticleWebActivity extends AppCompatActivity {

    WebView webView;
    Toolbar toolbar;

    String title = "", date = "", source = "", content = "", imgUrl = "", url = "";
    private ProgressBar progressBar;
    private float m_downX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web);

        toolbar = findViewById(R.id.toolbar);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        url = getIntent().getStringExtra("url");
        imgUrl = getIntent().getStringExtra("img");
        source = getIntent().getStringExtra("source");
        date = getIntent().getStringExtra("date");

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(1);

        try{
            webView.loadUrl(url);
//            initWebView();
//            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView webView, int progress){
                    progressBar.setProgress(progress);
                }
            });

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap ficon){
                    super.onPageStarted(view, url, ficon);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url){
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url){
                    progressBar.setVisibility(View.GONE);
                }

            });

        }catch (Exception ex){

        }


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Article");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new ViewPager.OnClickListener()
                                             {
                                                 @Override
                                                 public void onClick(View v){
                                                     finish();
                                                 }


                                             }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.article_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if(id == R.id.save_menu){
            return true;
        }
        else if(id == R.id.share_menu){
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }


}
