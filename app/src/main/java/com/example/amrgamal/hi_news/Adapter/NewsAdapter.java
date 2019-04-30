package com.example.amrgamal.hi_news.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amrgamal.hi_news.Data.Articles;
import com.example.amrgamal.hi_news.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AmrGamal on 26/04/2019.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.recyclerVH>
{
    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference().child("Articles");
    ArrayList<Articles> articlesArrayList;
    Context context;

    public NewsAdapter(ArrayList<Articles> name, Context context) {
        this.articlesArrayList = name;
        this.context = context;
    }
    @NonNull
    @Override
    public NewsAdapter.recyclerVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
        return new NewsAdapter.recyclerVH(view);        }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.recyclerVH holder,final int position) {
        final TextView title=holder.itemView.findViewById(R.id.title) ;
        title.setText(articlesArrayList.get(position).title);
        TextView description=holder.itemView.findViewById(R.id.description) ;
        description.setText(articlesArrayList.get(position).description);
        ImageView imageView =holder.itemView.findViewById(R.id.news_image);
        Picasso.with(context).load(articlesArrayList.get(position).photo_url).into(imageView);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(articlesArrayList.get(position).title).setValue(new Articles(articlesArrayList.get(position).title,null,articlesArrayList.get(position).photo_url,articlesArrayList.get(position).description));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    class recyclerVH extends RecyclerView.ViewHolder {
        Button button;
        public recyclerVH(@NonNull View itemView) {

            super(itemView);
            button = itemView.findViewById(R.id.important);
        }
    }
}
