package com.texgraphicscompany.biznewz.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.texgraphicscompany.biznewz.ArticleWebActivity;
import com.texgraphicscompany.biznewz.R;
import com.texgraphicscompany.biznewz.app.AppController;
import com.texgraphicscompany.biznewz.state_mngr.SessionFilters;

import java.util.List;

public class FilterItemsAdapter extends RecyclerView.Adapter<FilterItemsAdapter.FilterViewHolder>  {

        private List<String> items;
        private Context context;
        private ImageView selectedImg = null;
        private int selectedPosition = 0, type = -1; // type: 0 = country; 1 = source
        private SessionFilters sessionFilters;
        private String countrySelect, sourceSelect;


        private String TAG = "In recycler adapter: ";


        public FilterItemsAdapter(List<String> items, Context context, int type) {
            this.items = items;
            this.context = context;
            this.type = type;
            sessionFilters = new SessionFilters(context);
            countrySelect = sessionFilters.getCountryFilter();
            sourceSelect = sessionFilters.getSourceFilter();
        }

        @Override
        public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // inflating recycler item view
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.filter_item
                            , parent, false);

            return new FilterViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FilterViewHolder holder,  int mPosition) {

            final String article = items.get(mPosition);
            holder.titleView.setText(article);
            if(type == 0){
                Log.e("In filter recycle: ", "Country filter");
                if (countrySelect.equals("all")){

                    Log.e("In filter recycle: ", "All Countries");
                    if (mPosition == 0){
                        holder.photo.setVisibility(View.VISIBLE);
                        selectedImg = holder.photo;
                        selectedPosition = mPosition;
                    }
                }else{
                    Log.e("In filter recycle: ", "NOt All Countries- "+countrySelect);

                    if (article.equals(countrySelect)){
                        holder.photo.setVisibility(View.VISIBLE);
                        selectedImg = holder.photo;
                        selectedPosition = mPosition;
                    }
                }
            }else{
                if (sourceSelect.equals("all")){
                    if (mPosition == 0){
                        holder.photo.setVisibility(View.VISIBLE);
                        selectedImg = holder.photo;
                        selectedPosition = mPosition;
                    }
                }else{
                    if (article.equals(sourceSelect)){
                        holder.photo.setVisibility(View.VISIBLE);
                        selectedImg = holder.photo;
                        selectedPosition = mPosition;
                    }
                }
            }

//            holder.photo.setImageUrl(article.getImgUrl(), imageLoader);

            holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    /// button click event
                    if(selectedPosition != holder.getAdapterPosition()){
                        if(selectedImg!=null)
                            selectedImg.setVisibility(View.INVISIBLE);
                        holder.photo.setVisibility(View.VISIBLE);
                        selectedImg = holder.photo;
//                        notifyDataSetChanged();
//                        notifyItemChanged(selectedPosition);
                        selectedPosition = holder.getAdapterPosition();

                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            Log.v(FilterItemsAdapter.class.getSimpleName(),""+ items.size());
            return items.size();
        }

        /**
         * ViewHolder class
         */
        public class FilterViewHolder extends RecyclerView.ViewHolder {

            public TextView titleView;
            public ImageView photo;
            public RelativeLayout relativeLayout;

            public FilterViewHolder(View view) {
                super(view);
                titleView =  view.findViewById(R.id.itemText);
                photo = view.findViewById(R.id.selectIcn);
                relativeLayout = view.findViewById(R.id.filterItem);
            }
        }





    }
