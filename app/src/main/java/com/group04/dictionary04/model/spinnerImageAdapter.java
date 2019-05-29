package com.group04.dictionary04.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.group04.dictionary04.R;

public class spinnerImageAdapter extends BaseAdapter {
    Context context;
    int images[];
    String[] lang;
    LayoutInflater inflter;

    public spinnerImageAdapter(Context context, int[] flags, String[] lang) {
        this.context = context;
        this.images = flags;
        this.lang = lang;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public String getItem(int i) {
        return lang[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinnercustom, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(images[i]);
        names.setText(lang[i]);
        return view;
    }
}