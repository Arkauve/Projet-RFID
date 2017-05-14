package com.catpass.catpass;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jordan on 09/05/2017.
 */

public class HomeAdapter extends ArrayAdapter<Home> {

    ArrayList<Home> homeList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public HomeAdapter(Context context, int resource, ArrayList<Home> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        homeList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.homeAdress = (TextView) v.findViewById(R.id.lblAdress);
            holder.homeAdress.setTextColor(Color.BLACK);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.homeAdress.setText(homeList.get(position).getAdress());
        return v;

    }

    static class ViewHolder {
        public TextView homeAdress;
    }
}
