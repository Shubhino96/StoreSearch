package com.example.shubhangi.storesearch;

import android.content.Context;
import android.content.Loader;
import android.app.LoaderManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Grocery extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Storelist>> {

    final String GROCERY_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=18.975,%2072.825833&radius=500&type=grocery_or_supermarket&key=AIzaSyDihnpkEId44HFnz27y0BzTSz0wQinb-Yo";
    private StorelistAdapter adapter;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        ListView listView = (ListView) findViewById(R.id.list);
        adapter = new StorelistAdapter(this, 0, new ArrayList<Storelist>());
        listView.setAdapter(adapter);

        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0,null,this);
        }
        else
        {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            t1=(TextView)findViewById(R.id.connection);
            t1.setText("No Internet Connection");
        }
    }

    public Loader<List<Storelist>> onCreateLoader(int i, Bundle bundle) {
        return new Storeloader(this, GROCERY_URL);
    }

    public void onLoadFinished(Loader<List<Storelist>> loader, List<Storelist> list) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        if (list != null) {
            adapter.addAll(list);
        }
    }

    public void onLoaderReset(Loader<List<Storelist>> loader) {
        adapter.clear();
    }
}
