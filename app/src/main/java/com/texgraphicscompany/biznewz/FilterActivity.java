package com.texgraphicscompany.biznewz;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.texgraphicscompany.biznewz.adapters.FilterItemsAdapter;
import com.texgraphicscompany.biznewz.fragment.FilterItemFragment;
import com.texgraphicscompany.biznewz.state_mngr.SessionFilters;
import com.texgraphicscompany.biznewz.utils.FilterParams;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView countrySelect, sourceSelect, dateSelect, selectedOption = null;
    Button applyBtn, resetBtn;
    private SessionFilters sessionFilters;
    LinearLayout dateFilter;

    int type, selectedNews;

    private Activity mActivity;
    private String TAG = "In recycler filterList";
    private RecyclerView recyclerView;
    private List<String> details;
    private String fromDate, toDate;

    private FilterItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        mActivity = this;

        sessionFilters = new SessionFilters(this);

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

        selectedNews = getIntent().getIntExtra("news_type", 0);



        countrySelect = findViewById(R.id.countrySelect);
        sourceSelect = findViewById(R.id.sourceSelect);
        dateSelect = findViewById(R.id.dateRangeSelect);
        applyBtn = findViewById(R.id.applyBtn);
        resetBtn = findViewById(R.id.resetBtn);

        if(selectedNews==0){
            selectOption(sourceSelect);
            selectedOption = sourceSelect;
            countrySelect.setVisibility(View.GONE);
            type = 1;
        }else if(selectedNews==1){
            selectOption(countrySelect);
            selectedOption = countrySelect;
            sourceSelect.setVisibility(View.GONE);
            type = 0;
        }

//        initView();
        initRView();


        applyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
                finish();

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
                if(type == 0){
                    sessionFilters.setCountryFilter("all");
                }else{
                    sessionFilters.setSourceFilter("");

                }
                itemsAdapter.notifyDataSetChanged();

            }
        });

        countrySelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
            if (selectedOption!=countrySelect){
                if (selectedOption!=null)
                    deselectOption(selectedOption);
                selectedOption = countrySelect;
                selectOption(selectedOption);

                recyclerView.setVisibility(View.VISIBLE);
                dateFilter.setVisibility(View.GONE);
            }

            }
        });

        dateSelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
            if (selectedOption!=dateSelect){
                if (selectedOption!=null)
                    deselectOption(selectedOption);
                selectedOption = dateSelect;
                selectOption(selectedOption);

                dateFilter.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);

            }

            }
        });

        sourceSelect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
            if (selectedOption!=sourceSelect){
                if (selectedOption!=null)
                    deselectOption(selectedOption);
                selectedOption = sourceSelect;
                selectOption(selectedOption);
            }

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

    private void selectOption(TextView textView){
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorWhite));
    }
    private void deselectOption(TextView textView){
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorDarkGrey));
    }

    private void initRView(){

        recyclerView = findViewById(R.id.itemRecycler);
        dateFilter = findViewById(R.id.date_filter);

        dateFilter.setVisibility(View.GONE);

        details = new ArrayList<>();

        if(type == 0){
            details.add("All Countries");
            details.addAll(new ArrayList<>(new FilterParams().getCountries().keySet()));
        }

        itemsAdapter = new FilterItemsAdapter(details, mActivity, type);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(itemsAdapter);

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
