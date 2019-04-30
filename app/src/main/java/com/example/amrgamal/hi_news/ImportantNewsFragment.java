package com.example.amrgamal.hi_news;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amrgamal.hi_news.Adapter.NewsAdapter;
import com.example.amrgamal.hi_news.Data.Articles;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ImportantNewsFragment extends Fragment {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ArrayList<Articles> articles= new ArrayList<>();
    Context context;
    public ImportantNewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_important_news, container, false);
        firebaseDatabase= FirebaseDatabase.getInstance();
        context  = this.getContext();
        final String[] news = new String[1];
        recyclerView= view.findViewById(R.id.recycler_important);
        final NewsAdapter newsAdapter= new NewsAdapter(articles,context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(newsAdapter);

        databaseReference=firebaseDatabase.getReference().child(getString(R.string.articles));
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    articles.add(dataSnapshot.getValue(Articles.class));
                    news[0] =articles.get(0).description;
                    addToWidget(news[0]);
                    newsAdapter.notifyDataSetChanged();
                }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return view;
    }
    private void addToWidget(String news) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, HiWidget.class));
        HiWidget.callUpdateAppWidget(context,appWidgetManager,news,appWidgetIds);
    }
}
