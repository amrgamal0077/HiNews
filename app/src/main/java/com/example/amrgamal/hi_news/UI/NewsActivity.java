package com.example.amrgamal.hi_news.UI;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.amrgamal.hi_news.Adapter.PageAdapter;
import com.example.amrgamal.hi_news.Data.Articles;
import com.example.amrgamal.hi_news.Loader.ArticleLoader;
import com.example.amrgamal.hi_news.News;
import com.example.amrgamal.hi_news.R;

import java.util.ArrayList;

import butterknife.BindView;

public class NewsActivity extends AppCompatActivity {


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        tabLayout = findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        mViewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0)
                {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(NewsActivity.this,R.color.colorPrimaryDark));
                }
                else if(tab.getPosition()==1)
                {
                    tabLayout.setBackgroundColor(ContextCompat.getColor(NewsActivity.this,R.color.colorPrimaryDark));

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(mViewPager);
    }

//
//    @Override
//    public Loader<ArrayList<Articles>> onCreateLoader(int id, Bundle args) {
//        return new ArticleLoader(baseurl+apikey,NewsActivity.this);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<ArrayList<Articles>> loader, ArrayList<Articles> data) {
//        News.fetch(data);
//
//
//
//    @Override
//    public void onLoaderReset(Loader<ArrayList<Articles>> loader) {
//
//    }
}
