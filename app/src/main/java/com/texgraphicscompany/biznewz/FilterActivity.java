package com.texgraphicscompany.biznewz;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.texgraphicscompany.biznewz.fragment.FilterItemFragment;

public class FilterActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView countrySelect, sourceSelect, dateSelect, selectedOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Filter");
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

        countrySelect = findViewById(R.id.countrySelect);
        sourceSelect = findViewById(R.id.sourceSelect);
        dateSelect = findViewById(R.id.dateRangeSelect);

        initView();

        countrySelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event


            }
        });



    }

    private void initView(){
        Fragment fragment = new FilterItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        fragment.setArguments(bundle);

        loadFragment(fragment);
    }



    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.optionPane, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
