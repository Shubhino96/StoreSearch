package com.example.shubhangi.storesearch;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by shubhangi on 25-02-2017.
 */

public class Storeloader extends AsyncTaskLoader<List<Storelist>> {

    private String urld;
    public Storeloader(Context context,String url)
    {
        super(context);
        urld=url;
    }
    protected void onStartLoading()
    {
        forceLoad();
    }


    @Override
    public List<Storelist> loadInBackground() {
        if(urld==null) {
            return null;
        }
        List<Storelist> strlist=Queryutils.fetchdata(urld);
        return strlist;
    }
}
