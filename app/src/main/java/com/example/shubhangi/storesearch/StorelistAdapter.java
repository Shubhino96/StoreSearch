package com.example.shubhangi.storesearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by shubhangi on 20-02-2017.
 */

public class StorelistAdapter extends ArrayAdapter<Storelist> {
    public StorelistAdapter(Context context, int resource, List<Storelist> objects) {
        super(context, resource, objects);
    }
    public View getview(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview, parent, false);
            Storelist s1=getItem(position);
            TextView name =(TextView)listItemView.findViewById(R.id.first);
            name.setText(s1.getstart());
            TextView detalis=(TextView)listItemView.findViewById(R.id.inform);
            detalis.setText(s1.getinform());
        }
        Storelist s1=getItem(position);
        TextView name =(TextView)listItemView.findViewById(R.id.first);
        name.setText(s1.getstart());
        TextView detalis=(TextView)listItemView.findViewById(R.id.inform);
        detalis.setText(s1.getinform());
        return listItemView;

    }

}
