package com.pku.pkuapp.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.MyLog;

/**
 * Created by admin on 15/12/30.
 */
public class ClassesAdapter extends BaseAdapter {

    private Context context;
    private String[] classes;

    public ClassesAdapter(Context context, String[] classes) {
        this.classes = classes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return classes.length;
    }

    @Override
    public Object getItem(int i) {
        return classes[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.setting_item, null);
        }
        TextView titleView = (TextView) view.findViewById(R.id.tv_item_name);
        view.findViewById(R.id.iv_arrow).setVisibility(View.GONE);
        titleView.setText(classes[i]);
        return view;
    }
}
