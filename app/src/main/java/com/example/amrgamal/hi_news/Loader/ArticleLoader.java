package com.example.amrgamal.hi_news.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.amrgamal.hi_news.Data.Articles;
import com.example.amrgamal.hi_news.Utiles.ArticleUtiles;

import java.util.ArrayList;

/**
 * Created by AmrGamal on 25/04/2019.
 */

public class ArticleLoader extends android.support.v4.content.AsyncTaskLoader<ArrayList<Articles>> {

    private final String url;

    public ArticleLoader(String Url, Context context) {
        super(context);
        url = Url;

    }

    @Override
    public ArrayList<Articles> loadInBackground() {
        if (url == null)
            return null;
        return ArticleUtiles.fetchdata(url);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}

