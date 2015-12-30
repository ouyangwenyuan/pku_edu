package com.pku.pkuapp.ui;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;
import com.pku.pkuapp.base.BaseFragment;
import com.pku.pkuapp.base.MyConfig;
import com.pku.pkuapp.widget.PagerSlidingTabView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 15/12/30.
 */
public class UnionDepartActivity extends BaseActivity {
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
        for (int i = 0; i < 2; i++) {
            BaseFragment fragment = new MysteryFragment();
            fragments.add(fragment);
        }

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments, MyConfig.union_depart_tabs));
        viewPager.setCurrentItem(0);
    }

}
