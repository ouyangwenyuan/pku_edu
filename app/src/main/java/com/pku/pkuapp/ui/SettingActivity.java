package com.pku.pkuapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;

/**
 * Created by admin on 15/12/28.
 */
public class SettingActivity extends BaseActivity {
    private static final String menuItems[] = {"关于我们", "点评一下", "分享一下", "反馈意见", "使用条款", "商务合作", "清除缓存", "应用推荐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        LinearLayout ll_parent = (LinearLayout) findViewById(R.id.ll_setting_parent);
        SettingAdapter adapter = new SettingAdapter();
        int count = menuItems.length;
        for (int i = 0; i < count; i++) {
            View child = adapter.getView(i, null, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i == 4 || i == 6) {
                params.topMargin = (int) getResources().getDimension(R.dimen.fifteen_dip);
                ll_parent.addView(child, params);
            } else if (i == 0) {
                params.topMargin = (int) getResources().getDimension(R.dimen.ten_dip);
                ll_parent.addView(child, params);
            } else {
                params.topMargin = (int) getResources().getDimension(R.dimen.one_dip);
                if (i == count - 1) {
                    params.bottomMargin = (int) getResources().getDimension(R.dimen.fifteen_dip);
                }
                ll_parent.addView(child, params);
            }
        }
        TextView logoutView = new TextView(this);
        logoutView.setText("退出登录");
        logoutView.setGravity(Gravity.CENTER);
        logoutView.setBackgroundColor(Color.WHITE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.fourty_eight_dip));
        ll_parent.addView(logoutView, params);

    }

    private class SettingAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return menuItems.length;
        }

        @Override
        public Object getItem(int i) {
            return menuItems[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(SettingActivity.this).inflate(R.layout.setting_item, null);
            }
            TextView title = (TextView) view.findViewById(R.id.tv_item_name);
            title.setText(menuItems[i]);
            return view;
        }
    }

}
