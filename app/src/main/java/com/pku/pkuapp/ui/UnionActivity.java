package com.pku.pkuapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;
import com.pku.pkuapp.base.BaseFragment;
import com.pku.pkuapp.base.MyConfig;
import com.pku.pkuapp.base.MyLog;
import com.pku.pkuapp.widget.PagerSlidingTabView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15/12/30.
 */
public class UnionActivity extends BaseActivity {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship);
        initViewpager();
        /**
         * slidingTabview
         */
        PagerSlidingTabView slidView = (PagerSlidingTabView) findViewById(R.id.pager_tabs);
        slidView.setViewPager(viewPager);
        slidView.setAllCaps(false);
        slidView.setShouldExpand(true);
        slidView.setIndicatorHeight((int) getResources().getDimension(R.dimen.three_dip));
        slidView.setTypeface(null, Typeface.NORMAL);
        slidView.setPadding(5, 28, 5, 28);
        slidView.setBackgroundColor(Color.WHITE);
        slidView.setTextColor(Color.BLACK);
        slidView.setIndicatorColor(getResources().getColor(R.color.main_theme));

    }

    private void initViewpager() {
        viewPager = (ViewPager) this.findViewById(R.id.viewpager);
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();
        BaseFragment fragment = new MysteryFragment();
        fragments.add(fragment);
        BaseFragment fragment2 = new UnionIntroFragment();
        fragments.add(fragment2);

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, MyConfig.union_tabs));
        viewPager.setCurrentItem(0);
    }

}
