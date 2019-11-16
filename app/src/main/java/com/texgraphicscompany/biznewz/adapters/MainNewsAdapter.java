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
import com.android.volley.toolbox.NetworkImageView;
import com.texgraphicscompany.biznewz.ArticleWebActivity;
import com.texgraphicscompany.biznewz.NewsActivity;
import com.texgraphicscompany.biznewz.R;
import com.texgraphicscompany.biznewz.app.AppController;
import com.texgraphicscompany.biznewz.models.ArticleDetails;
import com.texgraphicscompany.biznewz.utils.GeneralUtils;

import java.text.ParseException;
import java.util.List;

public class MainNewsAdapter  extends RecyclerView.Adapter<MainNewsAdapter.ArticleViewHolder>  {

        private List<ArticleDetails> articles;
        private Context context;

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();


        private String TAG = "In recycler adapter: ";



        public MainNewsAdapter(List<ArticleDetails> articles, Context context) {
            this.articles = articles;
            this.context = context;
        }

        @Override
        public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // inflating recycler item view
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.custom2
                            , parent, false);

            return new ArticleViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ArticleViewHolder holder, int position) {

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();

            final int mPosition = position;
            final ArticleDetails article = articles.get(mPosition);
            holder.titleView.setText(article.getTitle());
            holder.sourceView.setText(article.getSource());

                holder.dateView.setText(article.getDate());


            holder.photo.setImageUrl(article.getImgUrl(), imageLoader);

            holder.articeCard.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    /// button click event
                    Intent intent = new Intent(context, ArticleWebActivity.class);
                    Bundle bundle= new Bundle();
//                    Log.v("From list adapter: ", "In intent: ");
                    bundle.putString("title", article.getTitle());
                    bundle.putString("content", article.getArticle());
                    bundle.putString("date", article.getDate());
                    bundle.putString("source", article.getSource());
                    bundle.putString("img", article.getImgUrl());
                    bundle.putString("url", article.getUrl());
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            Log.v(MainNewsAdapter.class.getSimpleName(),""+articles.size());
            return articles.size();
        }

        /**
         * ViewHolder class
         */
        public class ArticleViewHolder extends RecyclerView.ViewHolder {

            public TextView titleView, sourceView, dateView;
            public NetworkImageView photo;
            public RelativeLayout articeCard;

            public ArticleViewHolder(View view) {
                super(view);
                titleView =  view.findViewById(R.id.titleText);
                sourceView = view.findViewById(R.id.sourceText);
                dateView = view.findViewById(R.id.dateText);
                photo = view.findViewById(R.id.articleImg);
                articeCard = view.findViewById(R.id.articleCard);
            }
        }





    }
