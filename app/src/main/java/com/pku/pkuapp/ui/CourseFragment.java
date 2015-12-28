package com.pku.pkuapp.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.pku.pkuapp.R;
import com.pku.pkuapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseFragment extends BaseFragment {

    private static final String weeks[] = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private static final String times[] = {"08:00", "09:00", "10:10", "11:10", "13:00", "14:00", "15:10", "16:10", "17:10", "18:40", "19:40", "20:40",};

    public static class Course {
        public Date date;
        public String time;
        public int section;
        public int[] courseStates;
    }

    private static final int courses[][] = {{0, 1, 0, 0, 0, 1, 0}, {0, 1, 1, 0, 1, 0, 1}, {1, 1, 0, 1, 0, 1, 0}, {1, 0, 0, 1, 0, 1, 1}, {0, 0, 1, 1, 0, 1, 0}, {0, 1, 0, 1, 1, 0, 0}, {0, 1, 1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 1}, {0, 1, 1, 0, 1, 1, 0}, {1, 1, 0, 1, 0, 1, 1}, {1, 0, 0, 1, 0, 0, 1}, {0, 0, 1, 1, 0, 1, 0}};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_1, container, false);
        TextView titleView = (TextView) view.findViewById(R.id.tv_title_text);
        titleView.setText("课表");
        TextView leftView = (TextView) view.findViewById(R.id.iv_left_btn);
        leftView.setText("刷新");
        leftView.setVisibility(View.VISIBLE);
        leftView.setTextColor(getResources().getColor(R.color.main_theme));
        LinearLayout weekParent = (LinearLayout) view.findViewById(R.id.ll_week_parent);
        setWeek(weekParent);
        ListView lv = (ListView) view.findViewById(R.id.lv_course_time);
        List<Course> courseList = fetchCourse();
        lv.setAdapter(new CourseAdapter(inflater, courseList));
        return view;
    }

    private List<Course> fetchCourse() {
        List<Course> courseList = new ArrayList<Course>();
        for (int i = 0; i < times.length; i++) {
            Course course = new Course();
            course.time = times[i];
            course.courseStates = courses[i];
            course.section = i + 1;
            courseList.add(course);
        }
        return courseList;
    }

    private void setWeek(LinearLayout weekParent) {
        for (int i = 0; i < weeks.length; i++) {
            TextView textView = new TextView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            textView.setLayoutParams(params);
            textView.setText(weeks[i]);
            weekParent.addView(textView);
        }
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


    private class CourseAdapter extends BaseAdapter {
        LayoutInflater inflater;
        List<Course> courseList;

        public CourseAdapter(LayoutInflater inflater, List<Course> courseList) {
            this.inflater = inflater;
            this.courseList = courseList;
        }

        @Override
        public int getCount() {
            return courseList.size();
        }

        @Override
        public Object getItem(int i) {
            return courseList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = inflater.inflate(R.layout.lv_course_item, null);
            }
            TextView timeView = (TextView) view.findViewById(R.id.tv_time);
            TextView sectionView = (TextView) view.findViewById(R.id.tv_course);
            LinearLayout ll_parent = (LinearLayout) view.findViewById(R.id.ll_course_parent);
            Course course = courseList.get(i);
            timeView.setText(course.time);
            sectionView.setText(String.valueOf(course.section));
            int[] sections = course.courseStates;
            for (int j = 0; j < sections.length; j++) {
                TextView textView = new TextView(getActivity());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                params.gravity = Gravity.CENTER;
                textView.setLayoutParams(params);
                textView.setText(String.valueOf(sections[j]));
                if (sections[j] == 0) {
                    textView.setBackgroundColor(Color.RED);
                } else {
                    textView.setBackgroundColor(Color.GREEN);
                }
                ll_parent.addView(textView);
            }
            return view;
        }

    }
}
