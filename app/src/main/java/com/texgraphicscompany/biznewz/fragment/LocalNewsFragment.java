package com.texgraphicscompany.biznewz.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.texgraphicscompany.biznewz.R;
import com.texgraphicscompany.biznewz.adapters.MainNewsAdapter;
import com.texgraphicscompany.biznewz.app.AppController;
import com.texgraphicscompany.biznewz.models.ArticleDetails;
import com.texgraphicscompany.biznewz.utils.GeneralUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalNewsFragment extends Fragment {

    private Activity mActivity;
    private String TAG = "In recycler agentlist";
    private RecyclerView recyclerView;
    private List<ArticleDetails> details;

    private MainNewsAdapter agentsRecyclerAdapter;
    private ProgressDialog pDialog;

    public LocalNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if (context instanceof Activity){
            mActivity = (Activity) context;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_local_news, container, false);

        recyclerView = view.findViewById(R.id.articleRecycler);

        details = new ArrayList<>();

        agentsRecyclerAdapter = new MainNewsAdapter(details, mActivity);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(agentsRecyclerAdapter);

        getNewsData();

        return view;
    }


    private void loadArticles(String url){

        Log.e(TAG, "Stat reques");

        pDialog = new ProgressDialog(mActivity);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);


        String mJSONURLString = "http://pastebin.com/raw/2bW31yqa";

        // Initialize a new JsonObjectRequest instance
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response

                        try{

                            if (response.length()>0){
//                                JSONArray articleObj = response.getJSONArray("articles");
                                for(int i = 0; i < response.length(); i++){
                                    JSONObject obj = response.getJSONObject(i);

                                    ArticleDetails ad = new ArticleDetails();
                                    ad.setSource(obj.getString("source"));
                                    String title = obj.getString("title") + " - " + obj.getString("source");
                                    ad.setTitle(title);
                                    ad.setImgUrl(obj.getString("img_url"));
                                    ad.setUrl(obj.getString("url"));
//                                    ad.setArticle(obj.getString("content"));
                                    ad.setArticle("");

//                                    ad.setDate(GeneralUtils.convertToNewFormat(obj.getString("publishedAt")));
                                    ad.setDate(GeneralUtils.convertLToNewFormat(obj.getString("publish_date")));



                                    details.add(ad);
                                }
                            }




                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        agentsRecyclerAdapter.notifyDataSetChanged();
                        hidePDialog();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred
                        Log.e(TAG, ""+error.getMessage());
                        hidePDialog();
                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);



    }

    private void getNewsData(){
        Uri.Builder builder = new Uri.Builder();

//        https://biznewzapi.herokuapp.com/articles/

        builder.scheme("https")
                .authority("biznewzapi.herokuapp.com")
                .appendPath("articles/");
//                .appendQueryParameter("category", "business")
//                .appendQueryParameter("country", "us")
//                .appendQueryParameter("apiKey", "fd9c20a03ba04f8880116cf23a89b8d0");

//        String newsUrl = builder.build().toString();
        String newsUrl = "https://biznewzapi.herokuapp.com/articles/";

        Log.e("News CONSTRUCT", newsUrl);

        loadArticles(newsUrl);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
