package com.pku.pkuapp.ui;

import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;
import com.pku.pkuapp.base.MyLog;

public class MainActivity extends BaseActivity {
    private String tabStrings[];
    private int tabIcons[];
    private Class fragments[];
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabs();
    }

    private void initTabs() {
        //  sp = MeiyunApplication.getSharedPreferencesUtil();
        tabStrings = new String[]{getString(R.string.tab_main_name), getString(R.string.tab_course_name), getString(R.string.tab_mine_name)};
        tabIcons = new int[]{R.drawable.tab_main_bg, R.drawable.tab_course_bg, R.drawable.tab_mine_bg};
        fragments = new Class[]{Frag0.class, Frag1.class, Frag2.class};

        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < tabStrings.length; i++) {
            //setTabSpec(tabStrings[i], tabIcons[i], fragments[i]);
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabStrings[i]);
            tabSpec.setIndicator(getTabItemView(inflater, i));
            tabHost.addTab(tabSpec, fragments[i], null);
        }
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                MyLog.i("tabname = " + s);

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    private View getTabItemView(LayoutInflater inflater, int i) {
        View view = inflater.inflate(R.layout.main_tab_spec, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab_icon);
        imageView.setImageResource(tabIcons[i]);

        TextView textView = (TextView) view.findViewById(R.id.tv_tab_text);
        textView.setText(tabStrings[i]);
        return view;
    }
}
