package com.example.amrgamal.hi_news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.amrgamal.hi_news.Adapter.NewsAdapter;
import com.example.amrgamal.hi_news.Data.Articles;
import com.example.amrgamal.hi_news.Loader.ArticleLoader;
import com.example.amrgamal.hi_news.UI.NewsActivity;

import java.util.ArrayList;


public class News extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Articles>>{
   static   ArrayList<Articles>articlesArrayList;
    String apikey="&apiKey=8211dc747fad4a6f819cfd2b3514613f";
    String baseurl="https://newsapi.org/v2/top-headlines?country=";
    View view;
    RecyclerView   recyclerView ;
   public static String country;
    public News() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news2, container, false);
        getLoaderManager().initLoader(0,null,this);
        return view;

    }
    public static void fetch(ArrayList<Articles> articles)
    {
        articlesArrayList=articles;
    }


    @NonNull
    @Override
    public Loader<ArrayList<Articles>> onCreateLoader(int id, @Nullable Bundle args) {
        return new ArticleLoader(baseurl+country+apikey,this.getContext());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Articles>> loader, ArrayList<Articles> data) {

        recyclerView= view.findViewById(R.id.news_recycler);
        NewsAdapter newsAdapter= new NewsAdapter(data,this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Articles>> loader) {

    }
}