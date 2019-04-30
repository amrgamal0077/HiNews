package com.example.amrgamal.hi_news.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.amrgamal.hi_news.ImportantNewsFragment;
import com.example.amrgamal.hi_news.News;

/**
 * Created by AmrGamal on 26/04/2019.
 */

public class PageAdapter extends FragmentPagerAdapter {
    private   int numoftab;
    public PageAdapter(FragmentManager fm, int numoftab ) {
        super(fm);

        this.numoftab=numoftab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new News();


            case 1:
                return new ImportantNewsFragment();
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "News";
            case 1:
                return "Important News";

        }
        return "";
    }
    @Override
    public int getCount() {
        return numoftab;
    }

}
