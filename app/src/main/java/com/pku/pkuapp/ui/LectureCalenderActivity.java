package com.pku.pkuapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseActivity;
import com.pku.pkuapp.base.MyConfig;
import com.pku.pkuapp.base.MyLog;
import com.pku.pkuapp.widget.CalendarView;

/**
 * Created by admin on 15/12/29.
 */
public class LectureCalenderActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        final TextView monthView = (TextView) findViewById(R.id.tv_month);
        monthView.setText(calendarView.getCalendarYear() + "年" + calendarView.getCalendarMonth() + "月");
        calendarView.setOnCalendarDateChangedListener(new CalendarView.OnCalendarDateChangedListener() {
            @Override
            public void onCalendarDateChanged(int year, int month, int date) {
                monthView.setText(year + "年" + month + "月");
            }
        });
        LinearLayout weekParent = (LinearLayout) findViewById(R.id.ll_week_parent);
        setWeek(weekParent);

        ListView lv = (ListView) findViewById(R.id.lv_lecture);
        final String classes[] = MyConfig.classes;
        lv.setAdapter(new ClassesAdapter(this, classes));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyLog.i("click position" + i);
                Intent intent = new Intent(LectureCalenderActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setWeek(LinearLayout weekParent) {
        String[] weeks = MyConfig.weeks;
        for (int i = 0; i < weeks.length; i++) {
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            textView.setLayoutParams(params);
            textView.setText(weeks[i]);
            textView.setGravity(Gravity.CENTER);
            weekParent.addView(textView);
        }
    }
}
