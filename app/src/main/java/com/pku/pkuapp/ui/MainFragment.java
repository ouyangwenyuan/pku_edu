package com.pku.pkuapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;
import com.pku.pkuapp.base.BaseFragment;
import com.pku.pkuapp.base.MyLog;
import com.pku.pkuapp.widget.SlideShowView;

public class MainFragment extends BaseFragment {
    private static final String menus[] = {"研究生会", "教室查询", "实习工作", "成绩查询", "百讲演出", "讲座信息"};
    private static final int menuIcons[] = {R.drawable.menu_1, R.drawable.menu_2, R.drawable.menu_3, R.drawable.menu_4, R.drawable.menu_5, R.drawable.menu_6};
    private static final Class[] classes = {UnionActivity.class, ClassQueryActivity.class, InternShipActivity.class, MysteryActivity.class, ActQueryActivity.class, LectureCalenderActivity.class};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_0, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gv_menu);
//        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setAdapter(new MenuAdapter(inflater));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.i("click position" + i);
                Intent intent = new Intent(getActivity(), classes[i]); //new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", menus[i]);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();
    }


    private class MenuAdapter extends BaseAdapter {
        LayoutInflater inflater = null;

        public MenuAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return menus.length;
        }

        @Override
        public Object getItem(int i) {
            return menus[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = inflater.inflate(R.layout.main_menu_item, null);
            }

            ImageView icon = (ImageView) view.findViewById(R.id.iv_tab_icon);
            TextView textView = (TextView) view.findViewById(R.id.tv_tab_text);
            icon.setImageResource(menuIcons[i]);
            textView.setText(menus[i]);
            return view;
        }
    }
}
