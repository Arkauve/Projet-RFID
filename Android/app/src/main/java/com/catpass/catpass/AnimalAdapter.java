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

public class AnimalAdapter extends ArrayAdapter<Animal> {
    ArrayList<Animal> animalList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public AnimalAdapter(Context context, int resource, ArrayList<Animal> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        animalList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new AnimalAdapter.ViewHolder();
            v = vi.inflate(Resource, null);
            holder.animalName = (TextView) v.findViewById(R.id.lblName);
            holder.animalName.setTextColor(Color.BLACK);
            v.setTag(holder);
        } else {
            holder = (AnimalAdapter.ViewHolder) v.getTag();
        }
        holder.animalName.setText(animalList.get(position).getName());
        return v;

    }

    static class ViewHolder {
        public TextView animalName;
    }
}
