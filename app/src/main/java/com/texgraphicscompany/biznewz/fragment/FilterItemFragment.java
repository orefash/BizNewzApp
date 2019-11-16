package com.texgraphicscompany.biznewz.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.texgraphicscompany.biznewz.R;
import com.texgraphicscompany.biznewz.adapters.FilterItemsAdapter;
import com.texgraphicscompany.biznewz.adapters.MainNewsAdapter;
import com.texgraphicscompany.biznewz.utils.FilterParams;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterItemFragment extends Fragment {

    int type;

    private Activity mActivity;
    private String TAG = "In recycler agentlist";
    private RecyclerView recyclerView;
    private List<String> details;

    private FilterItemsAdapter itemsAdapter;


    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity = (Activity) context;
        }
    }

    public FilterItemFragment() {
        // Required empty public constructor
        type = 0;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_item, container, false);

        if (getArguments() != null && getArguments().getInt("type", -1) != -1) {

            type = getArguments().getInt("type", -1);

            recyclerView = view.findViewById(R.id.itemRecycler);

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

        return view;
    }

}
