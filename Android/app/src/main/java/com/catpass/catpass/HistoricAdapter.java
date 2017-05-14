package com.catpass.catpass;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jordan on 14/05/2017.
 */

public class HistoricAdapter extends ArrayAdapter<Historic> {
    ArrayList<Historic> historicList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public HistoricAdapter(Context context, int resource, ArrayList<Historic> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        historicList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new HistoricAdapter.ViewHolder();
            v = vi.inflate(Resource, null);
            holder.historicDate = (TextView) v.findViewById(R.id.lblDate);
            holder.historicDate.setTextColor(Color.BLACK);
            holder.historicOut = (TextView) v.findViewById(R.id.lblOut);
            holder.historicOut.setTextColor(Color.BLACK);
            v.setTag(holder);
        } else {
            holder = (HistoricAdapter.ViewHolder) v.getTag();
        }
        holder.historicDate.setText(historicList.get(position).getDate().toString());
        if(historicList.get(position).isOut())
            holder.historicOut.setText("Dehors");
        else
            holder.historicOut.setText("Maison");
        return v;

    }

    static class ViewHolder {
        public TextView historicDate;
        public TextView historicOut;
    }
}
