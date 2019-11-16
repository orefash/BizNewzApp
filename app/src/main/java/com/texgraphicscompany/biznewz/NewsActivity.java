package com.texgraphicscompany.biznewz;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {

    Toolbar toolbar;
    String title = "", date = "", source = "", content = "", imgUrl = "", url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        toolbar = findViewById(R.id.toolbar);

        try{
            title = getIntent().getStringExtra("title");
            content = getIntent().getStringExtra("content");
            url = getIntent().getStringExtra("url");
            imgUrl = getIntent().getStringExtra("img");
            source = getIntent().getStringExtra("source");
            date = getIntent().getStringExtra("date");

            TextView titleView = findViewById(R.id.titleText);
            TextView dateView = findViewById(R.id.dateText);
            TextView sourceView = findViewById(R.id.sourceText);
            TextView contentView = findViewById(R.id.bodyText);
//            TextView titleView = findViewById(R.id.titleText);

            titleView.setText(title);
            contentView.setText(content);
            sourceView.setText(source);
            dateView.setText(date);


        }catch (Exception ex){

        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
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
}
